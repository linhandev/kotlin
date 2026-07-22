// WITH_STDLIB
// LANGUAGE: +OverloadResolutionByLambdaReturnType
// OPT_IN: kotlin.experimental.ExperimentalTypeInference
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, using-lambda-return-type-to-refine-function-applicability -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda return type cannot refine when overload lambda parameter types are not SEERT
 */

import kotlin.OverloadResolutionByLambdaReturnType

@OverloadResolutionByLambdaReturnType
fun tie11403N(cb: Unit.() -> String): Unit {}

@OverloadResolutionByLambdaReturnType
fun tie11403N(cb: (Unit) -> Int): Unit {}

// TESTCASE NUMBER: 1
fun case_1(): Unit {
    val take = Unit
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>tie11403N<!> { 42 }
}
