// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 *                statements, assignments, simple-assignments -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: readonly List index assign reports NO_SET_METHOD
 */

// TESTCASE NUMBER: 1
fun test() {
    val xs = listOf(1, 2)
    <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xs<!NO_SET_METHOD!>[0]<!><!> = 3
}
