// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 24 -> sentence 24
 * NUMBER: 3
 * DESCRIPTION: ADD_ASSIGNMENT token used with custom plusAssign operator
 */
// TESTCASE NUMBER: 1

class Counter(var value: Int) {
    operator fun plusAssign(delta: Int) {
        value += delta
    }
}

fun box(): String {
    val counter = Counter(3)
    counter += 4
    return if (counter.value == 7) "OK" else "NOK"
}
