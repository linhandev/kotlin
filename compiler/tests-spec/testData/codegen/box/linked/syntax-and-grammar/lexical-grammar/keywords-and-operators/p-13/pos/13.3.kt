// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 13 -> sentence 13
 * NUMBER: 3
 * DESCRIPTION: ADD token used in compound expression a + b + c (operator precedence and left-associativity)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 3
    val b = 4
    val c = 6
    val result = a + b + c
    return if (result == 13) "OK" else "NOK"
}
