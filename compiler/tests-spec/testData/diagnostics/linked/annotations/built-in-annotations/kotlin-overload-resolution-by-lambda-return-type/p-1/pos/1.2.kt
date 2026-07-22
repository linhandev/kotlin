// FIR_IDENTICAL
// LANGUAGE: +OverloadResolutionByLambdaReturnType
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-overload-resolution-by-lambda-return-type -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: OverloadResolutionByLambdaReturnType may annotate generic extension overloads with lambda parameters
 */

// TESTCASE NUMBER: 1
annotation class OverloadResolutionByLambdaReturnType

@OverloadResolutionByLambdaReturnType
fun <T> List<T>.transform17772(fn: (T) -> Int): List<Int> = emptyList()

@OverloadResolutionByLambdaReturnType
fun <T> List<T>.transform17772(fn: (T) -> String): List<String> = emptyList()
