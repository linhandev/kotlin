// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                statements, assignments, simple-assignments -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: multi-parameter get/set pair enables matrix[i, j] read-write
 */

// TESTCASE NUMBER: 1
class Mat(val d: Array<IntArray>) {
    operator fun get(i: Int, j: Int): Int = d[i][j]
    operator fun set(i: Int, j: Int, v: Int) {
        d[i][j] = v
    }
}

fun test(): Int {
    val m = Mat(arrayOf(intArrayOf(0, 0), intArrayOf(0, 0)))
    m[1, 0] = 3
    return m[1, 0]
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
