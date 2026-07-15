// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: SUB_ASSIGNMENT token used in basic variable update var x -= 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 10
    x -= 3
    return if (x == 7) "OK" else "NOK"
}
