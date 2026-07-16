// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, indexing-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: m[1, 0] invokes operator fun get with two indices on Matrix
 */

// TESTCASE NUMBER: 1

class Matrix(val data: IntArray) {
    var getCalls = 0
    operator fun get(i: Int, j: Int): Int {
        getCalls++
        return data[i * 2 + j]
    }
}

fun box(): String {
    val m = Matrix(intArrayOf(1, 2, 3, 4))
    if (m[1, 0] != 3) return "NOK"
    if (m.getCalls != 1) return "NOK"
    return "OK"
}
