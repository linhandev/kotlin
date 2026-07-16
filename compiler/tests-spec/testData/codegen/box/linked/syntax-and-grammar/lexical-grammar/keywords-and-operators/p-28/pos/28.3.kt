// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 28 -> sentence 28
 * NUMBER: 3
 * DESCRIPTION: MOD_ASSIGNMENT token used with custom remAssign operator
 */
// TESTCASE NUMBER: 1

class Counter(var value: Int) {
    operator fun remAssign(mod: Int) {
        value %= mod
    }
}

fun box(): String {
    val counter = Counter(17)
    counter %= 5
    return if (counter.value == 2) "OK" else "NOK"
}
