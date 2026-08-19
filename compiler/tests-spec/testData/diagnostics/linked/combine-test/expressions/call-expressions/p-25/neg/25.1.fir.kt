// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 25 -> sentence 25
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: named argument type mismatch is rejected
 */

// TESTCASE NUMBER: 1
fun f(a: Int = 0): Int = a

fun test(): Int = f(a = <!ARGUMENT_TYPE_MISMATCH!>"x"<!>)
