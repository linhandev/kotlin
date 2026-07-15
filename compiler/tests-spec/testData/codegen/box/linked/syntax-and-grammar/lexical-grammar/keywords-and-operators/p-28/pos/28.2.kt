// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 28 -> sentence 28
 * NUMBER: 2
 * DESCRIPTION: MOD_ASSIGNMENT token used in digit extraction loop x %= 10
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 20
    x %= 6
    if (x == 2) return "OK"
    return "NOK"
}
