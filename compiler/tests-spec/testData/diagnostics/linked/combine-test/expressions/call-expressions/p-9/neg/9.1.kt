// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 9 -> sentence 9
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: unknown named parameter is rejected
 */

// TESTCASE NUMBER: 1
fun f(a: Int): Int = a

fun test(): Int = f(<!NAMED_PARAMETER_NOT_FOUND!>x<!> = 1)
