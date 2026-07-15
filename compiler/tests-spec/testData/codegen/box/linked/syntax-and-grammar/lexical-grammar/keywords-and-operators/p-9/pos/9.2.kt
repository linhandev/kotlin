// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 9 -> sentence 9
 * NUMBER: 2
 * DESCRIPTION: RCURL token closing class body and nested blocks
 */
// TESTCASE NUMBER: 1

class Counter {
    var count: Int = 0
        private set

    fun increment() {
        if (count < 10) {
            count = count + 1
        }
    }
}

fun box(): String {
    val c = Counter()
    c.increment()
    c.increment()
    return if (c.count == 2) "OK" else "NOK"
}
