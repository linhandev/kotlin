// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: list[1] = 42 invokes custom set operator at runtime
 */

class IndexedList {
    private val data = intArrayOf(0, 0, 0)
    var lastIndex = -1
    var lastValue = 0

    operator fun get(i: Int): Int = data[i]

    operator fun set(i: Int, v: Int) {
        lastIndex = i
        lastValue = v
        data[i] = v
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val list = IndexedList()
    list[1] = 42
    return if (list[1] == 42 && list.lastIndex == 1 && list.lastValue == 42) "OK" else "NOK"
}
