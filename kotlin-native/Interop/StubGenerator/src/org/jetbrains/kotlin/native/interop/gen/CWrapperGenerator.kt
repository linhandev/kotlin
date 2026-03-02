/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
package org.jetbrains.kotlin.native.interop.gen

import org.jetbrains.kotlin.native.interop.indexer.*

internal data class CCalleeWrapper(val lines: List<String>)

/**
 * Some functions don't have an address (e.g. macros-based or builtins).
 * To solve this problem we generate a wrapper function.
 */
internal class CWrappersGenerator(private val context: StubIrContext) {

    private var currentFunctionWrapperId = 0
    private val enableUndefinedApiProtection: Boolean = context.configuration.enableUndefinedApiProtection
    private val packageName =
            context.configuration.pkgName.replace(INVALID_CLANG_IDENTIFIER_REGEX, "_")

    private fun generateFunctionWrapperName(functionName: String): String {
        return "${packageName}_${functionName}_wrapper${currentFunctionWrapperId++}"
    }

    private fun bindSymbolToFunction(symbol: String, function: String): List<String> {
        val prefix = if (context.configuration.library.language == Language.CPP)
            "extern \"C\" "
        else
            ""

        return listOf(
                "${prefix}const void* $symbol __asm(${symbol.quoteAsKotlinLiteral()});",
                "${prefix}const void* $symbol = (const void*)&$function;"
        )
    }

    private data class Parameter(val type: String, val name: String)

    private fun createWrapper(
        symbolName: String,
        wrapperName: String,
        returnType: String,
        parameters: List<Parameter>,
        body: String,
    ): List<String> {
        val bodyLines = body.lines().filter { it.isNotEmpty() }
        val indentedBodyLines = bodyLines.map { "\t$it" }

        val header = listOf(
            "__attribute__((always_inline))",
            "$returnType $wrapperName(${parameters.joinToString { "${it.type} ${it.name}" }}) {"
        )
        val footer = listOf("}")

        return header +
            indentedBodyLines +
            footer +
            bindSymbolToFunction(symbolName, wrapperName)
    }

    private val Type.stringRepresentation get() = this.getStringRepresentation()

     // Detects pointer-to-pointer with const pointee (e.g. const char**); stringRepresentation drops const.
     private fun needsConstPtrPtr(type: Type): Boolean {
        val unwrapped = type.unwrapTypedefs()
        return when (unwrapped) {
            is PointerType -> {
                val inner = unwrapped.pointeeType.unwrapTypedefs()
                inner is PointerType && inner.pointeeIsConst
            }
            is IncompleteArrayType -> {
                val elem = unwrapped.elemType.unwrapTypedefs()
                elem is PointerType && elem.pointeeIsConst
            }
            else -> false
        }
    }

    // Generates const T** type string; avoids duplicate "const const".
    private fun generateConstPtrPtrType(type: Type, language: Language? = null): String {
        val unwrapped = type.unwrapTypedefs()
        val baseType = when (unwrapped) {
            is PointerType -> {
                val inner = unwrapped.pointeeType.unwrapTypedefs()
                if (inner is PointerType) inner.pointeeType.getStringRepresentation(language) else "void"
            }
            is IncompleteArrayType -> {
                val elem = unwrapped.elemType.unwrapTypedefs()
                if (elem is PointerType) elem.pointeeType.getStringRepresentation(language) else "void"
            }
            else -> "void"
        }
        val constPrefix = if (baseType.startsWith("const ")) "" else "const "
        return "$constPrefix$baseType**"
    }

    // Per-def preamble for undefined-API
    private fun generatePreambleLines(): List<String> {
        if (!enableUndefinedApiProtection || context.configuration.library.language != Language.CPP) return emptyList()
        return """
            // ========== undefined API protection preamble (weak symbol + &func == nullptr check) ==========
            namespace ohos {
                namespace interop {
                    extern "C" void ThrowIllegalStateExceptionFromCString(const char* message);
                }
            }
        """.trimIndent().lines()
    }


    private fun createCCalleeWrapper(function: FunctionDecl, symbolName: String): List<String> {
        assert(context.configuration.library.language != Language.CPP)

        val wrapperName = generateFunctionWrapperName(function.name)

        val returnType = function.returnType.stringRepresentation

        val parameters = function.parameters.mapIndexed { index, parameter ->
            val type = parameter.type.stringRepresentation
            Parameter(type, "p$index")
        }

        val callExpression = "${function.name}(${parameters.joinToString { it.name }})"

        val wrapperBody = if (function.returnType.unwrapTypedefs() is VoidType) {
            "$callExpression;"
        } else {
            "return (${returnType})($callExpression);"
        }
        return createWrapper(symbolName, wrapperName, returnType, parameters, wrapperBody)
    }

    private data class CppWrapperCommon(
        val wrapperName: String,
        val returnType: String,
        val returnTypePrefix: String,
        val parameters: List<Parameter>,
        val argumentTypes: List<String>,
        val callExpression: String,
    )

    private fun buildCppCalleeWrapperCommon(function: FunctionDecl): CppWrapperCommon {
        assert(context.configuration.library.language == Language.CPP)

        val cppLanguage = context.configuration.library.language
        val wrapperName = generateFunctionWrapperName(function.name)

        val unwrappedReturnType = function.returnType.unwrapTypedefs()
        val returnType = when {
            // Resolve C++ ambiguity when the function name equals the return struct name (e.g. mallinfo mallinfo()):
            // the cast (X)(X()) can be parsed with the first X as the function; use "struct X" for the type.
            unwrappedReturnType is RecordType && unwrappedReturnType.decl.spelling == function.name ->
                "struct ${unwrappedReturnType.decl.spelling}"
            else ->
                function.returnType.getStringRepresentation(cppLanguage)
        }
        val returnTypePrefix =
                if (unwrappedReturnType is PointerType && unwrappedReturnType.isLVReference) "&" else ""

        val parameters = function.parameters.mapIndexed { index, parameter ->
            val paramType = parameter.type
            val unwrappedParamType = paramType.unwrapTypedefs()

            val type = when {
                // For nested pointer types with const pointee (e.g., const char**), use const T**
                needsConstPtrPtr(paramType) -> generateConstPtrPtrType(paramType, cppLanguage)
                // For va_list typedef, use "va_list" instead of implementation details
                paramType is Typedef && paramType.def.name == "va_list" -> "va_list"
                else -> parameter.type.getStringRepresentation(cppLanguage)
            }
            Parameter(type, "p$index")
        }

        val argumentTypes = function.parameters.map { parameter ->
            val parameterTypeText = parameter.type.getStringRepresentation(cppLanguage)
            val type = parameter.type
            val unwrappedType = type.unwrapTypedefs()

            val cppRefTypePrefix =
                        if (unwrappedType is PointerType && unwrappedType.isLVReference) "*" else ""
            val typeExpression = when {
                // Cast const char** at call site; parameterTypeText would drop const
                needsConstPtrPtr(type) -> "(${generateConstPtrPtrType(type, cppLanguage)})"
                type is Typedef ->
                    "(${type.def.name})"
                type is PointerType && type.spelling != null ->
                    "(${type.spelling})$cppRefTypePrefix"
                unwrappedType is EnumType ->
                    "(${unwrappedType.def.spelling})"
                unwrappedType is RecordType ->
                    "(${unwrappedType.decl.spelling})"
                else ->
                    "$cppRefTypePrefix($parameterTypeText)"
            }

            typeExpression
        }

        val callExpression = run {
            assert(argumentTypes.size == parameters.size)
            val arguments = argumentTypes.mapIndexed { index, type ->
                "${type}(${parameters[index].name})"
            }
            "${function.name}(${arguments.joinToString()})"
        }

        return CppWrapperCommon(
            wrapperName = wrapperName,
            returnType = returnType,
            returnTypePrefix = returnTypePrefix,
            parameters = parameters,
            argumentTypes = argumentTypes,
            callExpression = callExpression,
        )
    }

    private fun createCppCalleeWrapper(function: FunctionDecl, symbolName: String): List<String> {
        val common = buildCppCalleeWrapperCommon(function)

        val wrapperBody = if (function.returnType.unwrapTypedefs() is VoidType) {
            "${common.callExpression};"
        } else {
            "return (${common.returnType})${common.returnTypePrefix}(${common.callExpression});"
        }
        return createWrapper(symbolName, common.wrapperName, common.returnType, common.parameters, wrapperBody)
    }

    private fun createCppCalleeWrapperWithFallback(function: FunctionDecl, symbolName: String): List<String> {
        val common = buildCppCalleeWrapperCommon(function)

        val symbolLiteral = function.name.replace("\\", "\\\\").replace("\"", "\\\"")
        val body = buildString {
            append("if (&${function.name} == nullptr) {")
            append("\n")
            append("    ohos::interop::ThrowIllegalStateExceptionFromCString(\"Missing API symbol: $symbolLiteral\");")
            append("\n")
            append("} else {")
            append("\n")
            if (function.returnType.unwrapTypedefs() is VoidType) {
                append("    ${common.callExpression};")
            } else {
                append("    return (${common.returnType})${common.returnTypePrefix}(${common.callExpression});")
            }
            append("\n")
            append("}")
        }
        return createWrapper(symbolName, common.wrapperName, common.returnType, common.parameters, body)
    }

    fun generateCCalleeWrapper(function: FunctionDecl, symbolName: String): CCalleeWrapper {
        if (function.isVararg) {
            return CCalleeWrapper(bindSymbolToFunction(symbolName, function.name))
        }

        val isCpp = context.configuration.library.language == Language.CPP
        if (enableUndefinedApiProtection && isCpp) {
            // Emit preamble for every wrapper that uses ThrowIllegalStateExceptionFromCString so that
            // when fragments are compiled separately (e.g. by Indexer) the namespace is visible.
            val preamble = generatePreambleLines()

            val weakDecl = function.declarationSpelling
                ?.takeIf { it.isNotBlank() }
                ?.let { "extern \"C\" __attribute__((weak)) $it;" }

            val wrapper = createCppCalleeWrapperWithFallback(function, symbolName)
            val wrapperLines = if (weakDecl != null) listOf(weakDecl) + wrapper else wrapper

            return CCalleeWrapper(preamble + wrapperLines)
        }
        val wrapperLines = if (isCpp) {
            createCppCalleeWrapper(function, symbolName)
        } else {
            createCCalleeWrapper(function, symbolName)
        }
        return CCalleeWrapper(wrapperLines)
    }

    fun generateCGlobalGetter(globalDecl: GlobalDecl, symbolName: String): CCalleeWrapper {
        val wrapperName = generateFunctionWrapperName("${globalDecl.name}_getter")
        // C++ support: preserve const on pointee so e.g. extern const char* generates "const char*" return type (C++ rejects char* = const char*).
        val returnType = if (context.configuration.library.language == Language.CPP) {
            when (val t = globalDecl.type) {
                is PointerType -> if (t.pointeeIsConst) "const ${t.pointeeType.stringRepresentation}*" else t.stringRepresentation
                else -> globalDecl.type.stringRepresentation
            }
        } else {
            globalDecl.type.stringRepresentation
        }
        val wrapperBody = "return ${globalDecl.name};"
        val wrapper = createWrapper(symbolName, wrapperName, returnType, emptyList(), wrapperBody)
        return CCalleeWrapper(wrapper)
    }

    fun generateCGlobalByPointerGetter(globalDecl: GlobalDecl, symbolName: String): CCalleeWrapper {
        val wrapperName = generateFunctionWrapperName("${globalDecl.name}_getter")
        val returnType = "void*"
        val wrapperBody = "return &${globalDecl.name};"
        val wrapper = createWrapper(symbolName, wrapperName, returnType, emptyList(), wrapperBody)
        return CCalleeWrapper(wrapper)
    }

    fun generateCGlobalSetter(globalDecl: GlobalDecl, symbolName: String): CCalleeWrapper {
        val wrapperName = generateFunctionWrapperName("${globalDecl.name}_setter")
        val globalType = globalDecl.type.stringRepresentation
        val parameter = Parameter(globalType, "p1")
        val wrapperBody = "${globalDecl.name} = ${parameter.name};"
        val wrapper = createWrapper(symbolName, wrapperName, "void", listOf(parameter), wrapperBody)
        return CCalleeWrapper(wrapper)
    }
}
