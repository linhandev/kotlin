// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: contains function without operator modifier does not participate in in-expression resolution, reports OPERATOR_MODIFIER_REQUIRED
 */

// TESTCASE NUMBER: 1
class Box
fun Box.contains(x: Int): Boolean = x > 0

fun test(x: Int): Boolean = x <!OPERATOR_MODIFIER_REQUIRED!>in<!> Box()
