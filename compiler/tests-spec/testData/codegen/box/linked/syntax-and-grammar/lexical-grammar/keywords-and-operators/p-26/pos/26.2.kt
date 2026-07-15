// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 26 -> sentence 26
 * NUMBER: 2
 * DESCRIPTION: MULT_ASSIGNMENT token used in loop product x *= i
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var product = 1
    for (i in 1..4) {
        product *= i
    }
    return if (product == 24) "OK" else "NOK"
}
