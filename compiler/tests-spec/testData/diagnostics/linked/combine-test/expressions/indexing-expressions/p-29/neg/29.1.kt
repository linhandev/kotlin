// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                statements, assignments, simple-assignments -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: set with wrong arity reports NO_VALUE_FOR_PARAMETER
 */

// TESTCASE NUMBER: 1
class Box(val a: IntArray) {
    operator fun get(i: Int): Int = a[i]
    operator fun set(i: Int, j: Int, v: Int) { a[i] = v }
}

fun test() {
    <!NO_VALUE_FOR_PARAMETER!>Box(intArrayOf(0))[0]<!> = 1
}
