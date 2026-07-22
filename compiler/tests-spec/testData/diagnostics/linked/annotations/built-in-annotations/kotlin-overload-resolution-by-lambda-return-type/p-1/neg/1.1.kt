// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-overload-resolution-by-lambda-return-type -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: stdlib OverloadResolutionByLambdaReturnType requires opt-in in Kotlin 1.9+
 */

// TESTCASE NUMBER: 1
import kotlin.OverloadResolutionByLambdaReturnType

@<!OPT_IN_USAGE_ERROR!>OverloadResolutionByLambdaReturnType<!>
fun create17774(producer: () -> Int): Int = 1

fun create17774(producer: () -> Double): Double = 1.0
