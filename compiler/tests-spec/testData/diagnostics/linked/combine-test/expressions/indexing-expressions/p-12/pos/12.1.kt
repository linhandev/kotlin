// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                statements, assignments, simple-assignments -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: custom operator set then get infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val data: IntArray) {
    operator fun get(i: Int): Int = data[i]
    operator fun set(i: Int, v: Int) { data[i] = v }
}

fun case1() {
    val b = Box(intArrayOf(1))
    b[0] = 8
    checkSubtype<Int>(b[0])
}
