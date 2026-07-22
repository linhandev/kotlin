// FIR_IDENTICAL
// LANGUAGE: +OverloadResolutionByLambdaReturnType
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-overload-resolution-by-lambda-return-type -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: OverloadResolutionByLambdaReturnType may be applied to overloaded functions with lambda parameters
 */

// TESTCASE NUMBER: 1
annotation class OverloadResolutionByLambdaReturnType

@OverloadResolutionByLambdaReturnType
fun create17771(f: (Int) -> Int): Int = 1

@OverloadResolutionByLambdaReturnType
fun create17771(f: (Int) -> String): String = ""
