// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 43 -> sentence 43
 * NUMBER: 5
 * DESCRIPTION: LE token in string literal "<="
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "<="
    return if (op[0] == '<' && op[1] == '=') "OK" else "NOK"
}
