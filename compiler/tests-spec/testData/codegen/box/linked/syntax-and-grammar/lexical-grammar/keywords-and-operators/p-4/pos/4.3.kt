// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: LPAREN token used in if condition and while condition
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 10
    if (x > 5) {
        var count = 0
        while (count < 3) {
            count = count + 1
        }
        if (count == 3) {
            return "OK"
        }
    }
    return "NOK"
}
