// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: ASSIGNMENT token used in variable reassignment var y = 0; y = 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var y = 0
    y = 1
    return if (y == 1) "OK" else "NOK"
}
