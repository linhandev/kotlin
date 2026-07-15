// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 22 -> sentence 22
 * NUMBER: 3
 * DESCRIPTION: SEMICOLON token used to separate multiple assignments in block
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 0
    var y = 0
    x = 1; y = 2
    return if (x == 1 && y == 2) "OK" else "NOK"
}
