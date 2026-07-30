// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                statements, assignments, simple-assignments -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: get-only type index assign reports NO_SET_METHOD
 */

// TESTCASE NUMBER: 1
class RO(val data: IntArray) {
    operator fun get(i: Int): Int = data[i]
}

fun test() {
    <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>RO(intArrayOf(1))<!NO_SET_METHOD!>[0]<!><!> = 2
}
