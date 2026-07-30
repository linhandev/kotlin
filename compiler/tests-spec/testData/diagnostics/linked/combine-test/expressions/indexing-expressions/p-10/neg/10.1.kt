// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                statements, assignments, simple-assignments -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: String index assign reports NO_SET_METHOD
 */

// TESTCASE NUMBER: 1
fun test() {
    <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>"ab"<!NO_SET_METHOD!>[0]<!><!> = 'z'
}
