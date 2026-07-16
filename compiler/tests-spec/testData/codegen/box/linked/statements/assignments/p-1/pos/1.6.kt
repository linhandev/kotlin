// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: m[0, 1] = 99 invokes multi-index set operator at runtime
 */

class Matrix {
    private val data = Array(2) { IntArray(2) }
    var lastI = -1
    var lastJ = -1
    var lastValue = 0

    operator fun get(i: Int, j: Int): Int = data[i][j]

    operator fun set(i: Int, j: Int, value: Int) {
        lastI = i
        lastJ = j
        lastValue = value
        data[i][j] = value
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val m = Matrix()
    m[0, 1] = 99
    return if (m[0, 1] == 99 && m.lastI == 0 && m.lastJ == 1 && m.lastValue == 99) "OK" else "NOK"
}
