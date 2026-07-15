// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 49 -> sentence 49
 * NUMBER: 5
 * DESCRIPTION: EQEQEQ token in string literal "==="
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "==="
    if (op.all { it == '=' }) return "OK"
    return "NOK"
}
