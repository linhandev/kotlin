// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 48 -> sentence 48
 * NUMBER: 5
 * DESCRIPTION: EQEQ token in string literal "=="
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "=="
    if (op.count { it == '=' } != 2) return "NOK"
    return "OK"
}
