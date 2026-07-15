// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: ADD_ASSIGNMENT token used in basic variable update var x += 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 10
    x += 5
    return if (x == 15) "OK" else "NOK"
}
