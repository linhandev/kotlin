// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 25 -> sentence 25
 * NUMBER: 2
 * DESCRIPTION: SUB_ASSIGNMENT token used in countdown loop x -= 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 5
    while (x > 0) {
        x -= 1
    }
    return if (x == 0) "OK" else "NOK"
}
