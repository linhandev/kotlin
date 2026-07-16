// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 50 -> sentence 50
 * NUMBER: 2
 * DESCRIPTION: SINGLE_QUOTE token in escaped quote character literal
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val q = '\''
    return if (q == '\'') "OK" else "NOK"
}
