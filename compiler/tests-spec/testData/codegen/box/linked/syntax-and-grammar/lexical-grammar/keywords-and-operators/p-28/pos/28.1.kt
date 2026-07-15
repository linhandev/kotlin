// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: MOD_ASSIGNMENT token used in basic variable update var x %= 3
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 10
    x %= 3
    return if (x == 1) "OK" else "NOK"
}
