// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                statements, assignments, simple-assignments -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: multi-parameter set then get infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Mat(val d: Array<IntArray>) {
    operator fun get(i: Int, j: Int): Int = d[i][j]
    operator fun set(i: Int, j: Int, v: Int) {
        d[i][j] = v
    }
}

fun case1() {
    val m = Mat(arrayOf(intArrayOf(0, 0), intArrayOf(0, 0)))
    m[1, 0] = 3
    checkSubtype<Int>(m[1, 0])
}
