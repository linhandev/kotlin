/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.llvm

import org.jetbrains.kotlin.backend.konan.NativeGenerationState
import org.jetbrains.kotlin.backend.konan.ir.getSuperClassNotAny
import org.jetbrains.kotlin.backend.konan.ir.implementedInterfaces
import org.jetbrains.kotlin.backend.konan.ir.konanLibrary
import org.jetbrains.kotlin.backend.konan.lower.getObjectClassInstanceFunction
import org.jetbrains.kotlin.backend.konan.requiresRtti
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.util.isAnnotationClass
import org.jetbrains.kotlin.ir.util.isInterface
import org.jetbrains.kotlin.ir.util.parentClassOrNull

import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.library.uniqueName
 

/**
 * The kind of IR reference that triggered a cross-klib usage record.
 */
internal enum class KlibReferenceKind {
    /** A regular function / operator call via [IrCall]. */
    CALL,
    /** A constructor call via [IrConstructorCall]. */
    CONSTRUCTOR_CALL,
    /** A delegating `super(...)` constructor call via [IrDelegatingConstructorCall]. */
    DELEGATING_CONSTRUCTOR_CALL,
    /** A field read via [IrGetField]. */
    FIELD_READ,
    /** A field write via [IrSetField]. */
    FIELD_WRITE,
    /** A class literal / reflection reference via [IrClassReference]. */
    CLASS_REFERENCE,
    /** A callable reference to a function via [IrFunctionReference]. */
    FUNCTION_REFERENCE,
    /** A callable reference to a property via [IrPropertyReference]. */
    PROPERTY_REFERENCE,
    /** An enum entry access via [IrGetEnumValue]. */
    ENUM_VALUE_ACCESS,
    VTABLE_ENTRY,
    INTERFACE_TABLE_ENTRY,
    SUPER_TYPE_INFO,
    INTERFACE_TYPE_INFO,
    TYPE_OPERATOR,
    CONSTRUCTED_CLASS_TYPE_INFO,
    CONSTANT_OBJECT_TYPE_INFO,
    CONSTANT_ARRAY_TYPE_INFO,
    CONSTANT_OBJECT_CONSTRUCTOR,
    ASSOCIATED_OBJECT_KEY_TYPE_INFO,
    ASSOCIATED_OBJECT_VALUE_TYPE_INFO,
    ASSOCIATED_OBJECT_INSTANCE_GETTER,

}

// Registry stores minimal per-module symbol info now.

/**
 * Stores all collected cross-klib references and provides efficient lookup APIs.
 *
 * Populated by [KlibCrossReferenceCollectorVisitor] during IR traversal and made available
 * on [NativeGenerationState] for use during LLVM IR generation.
 *
 * Only contains entries when at least one klib name was given to the visitor.
 */
internal class KlibCrossReferenceRegistry {
    // Map from referenced klib -> set of referencing klib names (exclude null / source module)
    private val referencedToReferencers = mutableMapOf<String, MutableSet<String>>()

    // Map from referenced klib -> set of symbols from that klib which are referenced by other modules
    private val referencedModuleToSymbols = mutableMapOf<String, MutableSet<IrSymbol>>()

    /**
     * Record that [symbol] (defined in [referencedKlibName]) is referenced from [referencingKlibName].
     */
    internal fun add(referencedKlibName: String, referencingKlibName: String?, symbol: IrSymbol) {
        if (referencingKlibName != null && referencingKlibName != referencedKlibName) {
            referencedToReferencers.getOrPut(referencedKlibName) { mutableSetOf() }.add(referencingKlibName)
        }

        referencedModuleToSymbols.getOrPut(referencedKlibName) { mutableSetOf() }.add(symbol)
    }

    /**
     * Given a referenced module name, return the list of module names that reference symbols
     * defined in that module. The source-compiled module (null) is not included in the result.
     */
    fun referencingModuleNamesOf(referencedKlibName: String): List<String> =
            referencedToReferencers[referencedKlibName]?.toList() ?: emptyList()

    /**
     * Return true if the given symbol is referenced by any module other than [moduleName].
     * [moduleName] may be null to indicate the source module.
     */
    fun isSymbolReferencedByOtherModules(symbol: IrSymbol, moduleName: String?): Boolean {
        if (moduleName == null) return false
        return referencedModuleToSymbols[moduleName]?.contains(symbol) ?: false
    }
}

/**
 * IR visitor that collects cross-klib symbol usages.
 *
 * For each IR expression that references a symbol (function calls, constructor calls, field reads/
 * writes, class references, etc.), the visitor checks whether:
 * 1. The **defining** klib of the referenced symbol is one of the [watchedKlibs].
 * 2. The reference site (the IR file that contains the expression) lives in a **different** klib
 *    (or in the source module being compiled).
 *
 * When both conditions are met, a [KlibCrossReferenceInfo] record is added to [registry].
 *
 * Usage:
 * ```kotlin
 * val visitor = KlibCrossReferenceCollectorVisitor(
 *     watchedKlibs = setOf("libFoo", "libBar"),
 *     registry = generationState.klibCrossReferenceRegistry,
 * )
 * irModule.acceptVoid(visitor)
 * ```
 */
internal class KlibCrossReferenceCollectorVisitor(
        private val nativeState: NativeGenerationState,
) : IrVisitorVoid() {

    // -----------------------------------------------------------------------
    // Context tracking: updated as we descend into IrFile nodes
    // -----------------------------------------------------------------------

    /** Unique name of the klib owning the file currently being visited, or null for the source module. */
    private var currentFileKlibName: String? = null

    /** Path of the IR file currently being visited. */
    private var currentFilePath: String = "<unknown>"

    // -----------------------------------------------------------------------
    // Visitor entry points
    // -----------------------------------------------------------------------

    /** Default: descend into children. */
    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    /**
     * Update the "current file" context and delegate to children.
     * We restore the previous context on exit so nested modules don't corrupt state
     * (even though in practice files don't nest within each other).
     */
    override fun visitFile(declaration: IrFile) {
        val previousKlibName = currentFileKlibName
        val previousFilePath = currentFilePath
        try {
            currentFileKlibName = try { declaration.konanLibrary?.uniqueName } catch (_: Throwable) { null }
            currentFilePath = declaration.fileEntry.name
            declaration.acceptChildrenVoid(this)
        } finally {
            currentFileKlibName = previousKlibName
            currentFilePath = previousFilePath
        }
    }

    override fun visitClass(declaration: IrClass) {
        recordVTableReferences(declaration)
        recordInterfaceTableReferences(declaration)
        recordTypeInfoReferences(declaration)
        recordAssociatedObjectReferences(declaration)
        declaration.acceptChildrenVoid(this)
    }

    private fun recordVTableReferences(irClass: IrClass) {
        if (!irClass.requiresRtti()) return
        val classKlibName = irClass.konanLibrary?.uniqueName
        val moduleIncludeOnly = nativeState.context.config.moduleIncludeOnly
        if (moduleIncludeOnly.isEmpty()) return

        // Interfaces do not have class vtables. Calling getImplementation() for interface
        // vtable entries may hit ClassLayoutBuilder checks such as "Expected a class".
        if (irClass.isInterface || irClass.isAnnotationClass) return
        if (classKlibName == null || classKlibName in moduleIncludeOnly) return

        val layoutBuilder = try {
            nativeState.context.getLayoutBuilder(irClass)
        } catch (_: Throwable) {
            return
        }

        layoutBuilder.vtableEntries.forEach { entry ->
            val implementation = try {
                entry.getImplementation(nativeState.context)
            } catch (_: Throwable) {
                null
            } ?: return@forEach
            recordIfCrossKlib(implementation.symbol, KlibReferenceKind.VTABLE_ENTRY)
        }
    }

    private fun recordInterfaceTableReferences(irClass: IrClass) {
        val moduleIncludeOnly = nativeState.context.config.moduleIncludeOnly
        if (moduleIncludeOnly.isEmpty()) return
        if (irClass.isInterface || irClass.isAnnotationClass) return

        val classKlibName = irClass.konanLibrary?.uniqueName ?: return
        if (classKlibName in moduleIncludeOnly) return

        val layoutBuilder = try {
            nativeState.context.getLayoutBuilder(irClass)
        } catch (_: Throwable) {
            return
        }

        irClass.implementedInterfaces.forEach { iface ->
            val ifaceLayout = try {
                nativeState.context.getLayoutBuilder(iface)
            } catch (_: Throwable) {
                return@forEach
            }

            ifaceLayout.interfaceVTableEntries.forEach { ifaceFunction ->
                val impl = try {
                    layoutBuilder.overridingOf(ifaceFunction)
                } catch (_: Throwable) {
                    null
                } ?: return@forEach
                recordIfCrossKlib(impl.symbol, KlibReferenceKind.INTERFACE_TABLE_ENTRY)
            }
        }
    }

    private fun recordTypeInfoReferences(irClass: IrClass) {
        val moduleIncludeOnly = nativeState.context.config.moduleIncludeOnly
        if (moduleIncludeOnly.isEmpty()) return

        val classKlibName = irClass.konanLibrary?.uniqueName ?: return
        if (classKlibName in moduleIncludeOnly) return

        irClass.getSuperClassNotAny()?.let {
            recordIfCrossKlib(it.symbol, KlibReferenceKind.SUPER_TYPE_INFO)
        }

        irClass.implementedInterfaces.forEach {
            recordIfCrossKlib(it.symbol, KlibReferenceKind.INTERFACE_TYPE_INFO)
        }
    }

    private fun recordAssociatedObjectReferences(irClass: IrClass) {
        val moduleIncludeOnly = nativeState.context.config.moduleIncludeOnly
        if (moduleIncludeOnly.isEmpty()) return

        val classKlibName = irClass.konanLibrary?.uniqueName
        if (classKlibName != null && classKlibName in moduleIncludeOnly) return

        val associatedObjects = try {
            nativeState.context.getLayoutBuilder(irClass).associatedObjects
        } catch (_: Throwable) {
            return
        }

        associatedObjects.forEach { (key, value) ->
            // RTTIGenerator.genAssociatedObjects
            recordIfCrossKlib(key.symbol, KlibReferenceKind.ASSOCIATED_OBJECT_KEY_TYPE_INFO)
            recordIfCrossKlib(value.symbol, KlibReferenceKind.ASSOCIATED_OBJECT_VALUE_TYPE_INFO)
            recordIfCrossKlib(
                    nativeState.context.getObjectClassInstanceFunction(value).symbol,
                    KlibReferenceKind.ASSOCIATED_OBJECT_INSTANCE_GETTER
            )
        }
    }

    override fun visitTypeOperator(expression: IrTypeOperatorCall) {
        expression.typeOperand.classOrNull?.let {
            recordIfCrossKlib(it, KlibReferenceKind.TYPE_OPERATOR)
        }
        expression.acceptChildrenVoid(this)
    }



    // -----------------------------------------------------------------------
    // Expression visitors — each checks the symbol and then recurses
    // -----------------------------------------------------------------------

    override fun visitCall(expression: IrCall) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.CALL)
        recordConstructedClassFromLoweredCall(expression)
        expression.acceptChildrenVoid(this)
    }

    private fun recordConstructedClassFromLoweredCall(expression: IrCall) {
        expression.type.classOrNull?.let { classSymbol ->
            recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTRUCTED_CLASS_TYPE_INFO)
        }
        expression.dispatchReceiver?.type?.classOrNull?.let { classSymbol ->
            recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTRUCTED_CLASS_TYPE_INFO)
        }
    }

    override fun visitConstructorCall(expression: IrConstructorCall) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.CONSTRUCTOR_CALL)
        expression.acceptChildrenVoid(this)
    }

    override fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.DELEGATING_CONSTRUCTOR_CALL)
        expression.acceptChildrenVoid(this)
    }

    override fun visitGetField(expression: IrGetField) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.FIELD_READ)
        expression.acceptChildrenVoid(this)
    }

    override fun visitSetField(expression: IrSetField) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.FIELD_WRITE)
        expression.acceptChildrenVoid(this)
    }

    override fun visitGetValue(expression: IrGetValue) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.FIELD_READ)
        expression.acceptChildrenVoid(this)
    }

    override fun visitSetValue(expression: IrSetValue) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.FIELD_WRITE)
        expression.acceptChildrenVoid(this)
    }

    override fun visitClassReference(expression: IrClassReference) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.CLASS_REFERENCE)
        expression.acceptChildrenVoid(this)
    }

    override fun visitFunctionReference(expression: IrFunctionReference) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.FUNCTION_REFERENCE)
        expression.acceptChildrenVoid(this)
    }

    override fun visitPropertyReference(expression: IrPropertyReference) {
        expression.symbol.let { recordIfCrossKlib(it, KlibReferenceKind.PROPERTY_REFERENCE) }
        expression.acceptChildrenVoid(this)
    }

    override fun visitGetEnumValue(expression: IrGetEnumValue) {
        recordIfCrossKlib(expression.symbol, KlibReferenceKind.ENUM_VALUE_ACCESS)
        expression.acceptChildrenVoid(this)
    }

    override fun visitConstantObject(expression: IrConstantObject) {
        recordIfCrossKlib(expression.constructor, KlibReferenceKind.CONSTANT_OBJECT_CONSTRUCTOR)
        expression.type.classOrNull?.let { classSymbol ->
            recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTANT_OBJECT_TYPE_INFO)
        }
        expression.constructor.owner.parentClassOrNull?.symbol?.let { classSymbol ->
            recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTANT_OBJECT_TYPE_INFO)
        }
        expression.typeArguments.forEach { type ->
            type.classOrNull?.let { classSymbol ->
                recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTANT_OBJECT_TYPE_INFO)
            }
        }
        expression.acceptChildrenVoid(this)
    }

    override fun visitConstantArray(expression: IrConstantArray) {
        expression.type.classOrNull?.let { classSymbol ->
            recordIfCrossKlib(classSymbol, KlibReferenceKind.CONSTANT_ARRAY_TYPE_INFO)
        }

        expression.acceptChildrenVoid(this)
    }



    // -----------------------------------------------------------------------
    // Core check
    // -----------------------------------------------------------------------

    /**
     * Check whether the given symbol represents a cross-klib usage.
     *
     * A cross-klib usage is recorded when:
     * 1. The symbol is bound (its owner declaration is accessible).
     * 2. The owner declaration belongs to one of the [watchedKlibs].
     * 3. The current file's klib is **different** from the defining klib (including the case
     *    where the current file is in the source module, i.e. [currentFileKlibName] is `null`).
     */
    private fun recordIfCrossKlib(symbol: IrSymbol, kind: KlibReferenceKind) {
        if (!symbol.isBound) return
        val declaration = symbol.owner as? IrDeclaration ?: return
        val symbolsToRecord = if (declaration is IrSimpleFunction && declaration.isFakeOverride) {
            declaration.resolveRealOverrides().map { it.symbol }
        } else {
            listOf(symbol)
        }

        for (resolvedSymbol in symbolsToRecord) {
            if (!resolvedSymbol.isBound) continue
            val resolvedDeclaration = resolvedSymbol.owner as? IrDeclaration ?: continue

            val referencedKlib = try { resolvedDeclaration.konanLibrary } catch (_: Throwable) { null } ?: continue
            val referencedKlibName = referencedKlib.uniqueName
            // Only care about symbols in the user-specified watched klibs
            // if (referencedKlibName !in watchedKlibs) return
            // Skip intra-klib references (usage site is the same klib as definition)
            if (currentFileKlibName == referencedKlibName) continue
            if (nativeState.context.config.moduleIncludeOnly.contains(currentFileKlibName)) continue

            nativeState.klibCrossReferenceRegistry.add(
                    referencedKlibName,
                    currentFileKlibName,
                    resolvedSymbol
            )
        }
    }

    private fun IrSimpleFunction.resolveRealOverrides(
            visited: MutableSet<IrSimpleFunction> = mutableSetOf()
    ): List<IrSimpleFunction> {
        if (!visited.add(this)) return emptyList()

        if (!isFakeOverride) return listOf(this)

        return overriddenSymbols
                .flatMap { it.owner.resolveRealOverrides(visited) }
                .distinct()
    }



    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    /**
     * Produce the fully qualified name for any [IrDeclaration], including those that are not
     * [IrDeclarationParent] (e.g. [IrProperty], [IrEnumEntry]).
     */
    
}
