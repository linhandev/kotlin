// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, indexing-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: b[1] invokes operator fun get on Box receiver
 */

// TESTCASE NUMBER: 1

class Box(val data: IntArray) {
    var getCalls = 0
    operator fun get(i: Int): Int {
        getCalls++
        return data[i]
    }
}

fun box(): String {
    val b = Box(intArrayOf(10, 20))
    if (b[1] != 20) return "NOK"
    if (b.getCalls != 1) return "NOK"
    return "OK"
}
