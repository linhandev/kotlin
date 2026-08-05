// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 23 -> sentence 23
 *                overload-resolution, building-the-overload-candidate-set-ocs, operator-call -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: 无 iterator 约定的类型不能 for-in
 */

// TESTCASE NUMBER: 1
class Box

fun test() { for (x in <!ITERATOR_MISSING!>Box()<!>) { } }
