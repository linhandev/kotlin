// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: DIV_ASSIGNMENT token used in basic variable update var x /= 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 20
    x /= 4
    return if (x == 5) "OK" else "NOK"
}
