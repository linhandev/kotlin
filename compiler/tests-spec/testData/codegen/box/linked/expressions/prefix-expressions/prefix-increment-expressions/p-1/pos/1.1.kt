// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, prefix-increment-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: prefix increment assigns inc result and returns it
 */

// TESTCASE NUMBER: 1

class Counter(var value: Int) {
    operator fun inc(): Counter {
        value++
        return this
    }
}

fun box(): String {
    var c = Counter(1)
    val result = ++c
    if (result !== c) return "NOK"
    if (c.value != 2) return "NOK"
    return "OK"
}
