/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: a[i]++ expands to get/set/inc operator calls and updates the stored element
 */

// TESTCASE NUMBER: 1
class Counter(var v: Int) {
    operator fun inc(): Counter {
        v++
        return this
    }
}

class Box {
    var inner = Counter(0)

    operator fun get(i: Int) = inner

    operator fun set(i: Int, c: Counter) {
        inner = c
    }
}

fun box(): String {
    val box = Box()
    box[0]++
    return if (box[0].v == 1) "OK" else "NOK: ${box[0].v}"
}
