// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 2
 * DESCRIPTION: DIV_ASSIGNMENT token used in repeated halving loop x /= 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 32
    repeat(3) {
        x /= 2
    }
    return if (x == 4) "OK" else "NOK"
}
