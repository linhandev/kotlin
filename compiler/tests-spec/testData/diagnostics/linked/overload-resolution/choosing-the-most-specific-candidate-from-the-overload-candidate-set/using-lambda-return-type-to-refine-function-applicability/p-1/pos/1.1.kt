// WITH_STDLIB
// LANGUAGE: +OverloadResolutionByLambdaReturnType
// OPT_IN: kotlin.experimental.ExperimentalTypeInference
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, using-lambda-return-type-to-refine-function-applicability -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda return type refines MSC to prefer overload with matching lambda result type
 */

import kotlin.OverloadResolutionByLambdaReturnType

@OverloadResolutionByLambdaReturnType
fun pick11403(f: (Unit) -> String): String = "str"

@OverloadResolutionByLambdaReturnType
fun pick11403(f: (Unit) -> Int): String = "int"

// TESTCASE NUMBER: 1
fun case_1(): String = pick11403 { 42 }
