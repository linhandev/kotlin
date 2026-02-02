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
            body: String
    ): List<String> = listOf(
            "__attribute__((always_inline))",
            "$returnType $wrapperName(${parameters.joinToString { "${it.type} ${it.name}" }}) {",
            "\t$body",
            "}",
            *bindSymbolToFunction(symbolName, wrapperName).toTypedArray()
    )

    private val Type.stringRepresentation get() = this.getStringRepresentation()

     // ohos cpp support:
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

    // ohos cpp support:
    // Generates const T** type string; avoids duplicate "const const".
    private fun generateConstPtrPtrType(type: Type): String {
        val unwrapped = type.unwrapTypedefs()
        val baseType = when (unwrapped) {
            is PointerType -> {
                val inner = unwrapped.pointeeType.unwrapTypedefs()
                if (inner is PointerType) inner.pointeeType.stringRepresentation else "void"
            }
            is IncompleteArrayType -> {
                val elem = unwrapped.elemType.unwrapTypedefs()
                if (elem is PointerType) elem.pointeeType.stringRepresentation else "void"
            }
            else -> "void"
        }
        val constPrefix = if (baseType.startsWith("const ")) "" else "const "
        return "$constPrefix$baseType**"
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

    private fun createCppCalleeWrapper(function: FunctionDecl, symbolName: String): List<String> {
        assert(context.configuration.library.language == Language.CPP)
        
        val wrapperName = generateFunctionWrapperName(function.name)

        val returnType = function.returnType.stringRepresentation
        val unwrappedReturnType = function.returnType.unwrapTypedefs()
        val returnTypePrefix =
                if (unwrappedReturnType is PointerType && unwrappedReturnType.isLVReference) "&" else ""

        val parameters = function.parameters.mapIndexed { index, parameter ->
            val paramType = parameter.type
            val unwrappedParamType = paramType.unwrapTypedefs()

            val type = when {
                // ohos cpp support:
                // If struct is passed by value (and not a typedef alias), convert to pointer
                // in wrapper function parameter declaration.
                unwrappedParamType is RecordType && paramType !is PointerType && paramType !is Typedef -> {
                    "${parameter.type.stringRepresentation}*"
                }
                // ohos cpp support:
                // For nested pointer types with const pointee (e.g., const char**), use const T**
                needsConstPtrPtr(paramType) -> generateConstPtrPtrType(paramType)
                // For va_list typedef, use "va_list" instead of implementation details
                paramType is Typedef && paramType.def.name == "va_list" -> "va_list"
                else -> parameter.type.stringRepresentation
            }
            Parameter(type, "p$index")
        }
        val argumentTypes = function.parameters.map { parameter ->
            val parameterTypeText = parameter.type.stringRepresentation
            val type = parameter.type
            val unwrappedType = type.unwrapTypedefs()
            
            val cppRefTypePrefix =
                        if (unwrappedType is PointerType && unwrappedType.isLVReference) "*" else ""
            val typeExpression = when {
                // ohos cpp support:
                // Cast const char** at call site; parameterTypeText would drop const
                needsConstPtrPtr(type) -> "(${generateConstPtrPtrType(type)})"
                type is Typedef ->
                    "(${type.def.name})"
                type is PointerType && type.spelling != null ->
                    "(${type.spelling})$cppRefTypePrefix"
                unwrappedType is EnumType ->
                    "(${unwrappedType.def.spelling})"
                unwrappedType is RecordType ->
                    "*(${unwrappedType.decl.spelling}*)"
                else ->
                    "$cppRefTypePrefix($parameterTypeText)"
            }

            typeExpression
        }

        val callExpression = with (function) {
            assert(argumentTypes.size == parameters.size)
            val arguments = argumentTypes.mapIndexed { index, type ->
                "${type}(${parameters[index].name})"
            }
            "${fullName}(${arguments.joinToString()})"
        }

        val wrapperBody = if (function.returnType.unwrapTypedefs() is VoidType) {
            "$callExpression;"
        } else {
            "return (${returnType})$returnTypePrefix($callExpression);"
        }
        return createWrapper(symbolName, wrapperName, returnType, parameters, wrapperBody)
    }

    fun generateCCalleeWrapper(function: FunctionDecl, symbolName: String): CCalleeWrapper =
            if (function.isVararg) {
                CCalleeWrapper(bindSymbolToFunction(symbolName, function.name))
            } else {
                val wrapper = if (context.configuration.library.language == Language.CPP) {
                    createCppCalleeWrapper(function, symbolName)
                } else {
                    createCCalleeWrapper(function, symbolName)
                }
                CCalleeWrapper(wrapper)
            }

    fun generateCGlobalGetter(globalDecl: GlobalDecl, symbolName: String): CCalleeWrapper {
        val wrapperName = generateFunctionWrapperName("${globalDecl.name}_getter")
        val returnType = globalDecl.type.stringRepresentation
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
