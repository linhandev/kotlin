// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: MULT_ASSIGNMENT token used in basic variable update var x *= 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 6
    x *= 2
    return if (x == 12) "OK" else "NOK"
}
