// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: RPAREN token closing nested parentheses ((a + b) * c)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 2
    val b = 3
    val c = 4
    val result = ((a + b) * c)
    return if (result == 20) "OK" else "NOK"
}
