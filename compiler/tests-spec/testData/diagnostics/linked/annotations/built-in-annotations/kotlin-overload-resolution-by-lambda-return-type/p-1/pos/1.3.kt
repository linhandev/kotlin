// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.experimental.ExperimentalTypeInference
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-overload-resolution-by-lambda-return-type -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: overload resolution selects overload by lambda return type with stdlib annotation
 */

// TESTCASE NUMBER: 1
import kotlin.OverloadResolutionByLambdaReturnType

@OverloadResolutionByLambdaReturnType
fun create17773(producer: () -> Int): Int = 1

fun create17773(producer: () -> Double): Double = 1.0

fun use17773(): Double = create17773 { 3.14 }
