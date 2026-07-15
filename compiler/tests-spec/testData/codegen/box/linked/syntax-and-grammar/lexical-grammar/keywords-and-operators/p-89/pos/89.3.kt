// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 3
 * DESCRIPTION: DO token in nested do-while loops
 */
// TESTCASE NUMBER: 1
fun nestedDo89(): String {
    var outer = 0
    do {
        var inner = 0
        do {
            inner++
        } while (inner < 1)
        outer += inner
    } while (outer < 1)
    return if (outer == 1) "OK" else "NOK"
}

fun box(): String = nestedDo89()
