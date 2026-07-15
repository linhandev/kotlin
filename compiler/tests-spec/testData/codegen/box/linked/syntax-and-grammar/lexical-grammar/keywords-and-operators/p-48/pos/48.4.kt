// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 48 -> sentence 48
 * NUMBER: 4
 * DESCRIPTION: EQEQ token in compound equality check x == 5 && y == 10
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 5
    val y = 10
    return if (x == 5 && y == 10) "OK" else "NOK"
}
