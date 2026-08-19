// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 7 -> sentence 7
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: call with no arguments when a required parameter has no default
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 0): Int = a + b

fun test(): Int = f<!NO_VALUE_FOR_PARAMETER!>()<!>
