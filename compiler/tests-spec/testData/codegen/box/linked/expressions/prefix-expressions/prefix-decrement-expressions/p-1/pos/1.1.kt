// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, prefix-decrement-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: prefix decrement assigns dec result and returns it
 */

// TESTCASE NUMBER: 1

class Counter(var value: Int) {
    operator fun dec(): Counter {
        value--
        return this
    }
}

fun box(): String {
    var c = Counter(2)
    val result = --c
    if (result !== c) return "NOK"
    if (c.value != 1) return "NOK"
    return "OK"
}
