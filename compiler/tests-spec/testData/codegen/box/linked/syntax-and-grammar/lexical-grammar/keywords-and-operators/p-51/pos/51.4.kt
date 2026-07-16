// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 51 -> sentence 51
 * NUMBER: 4
 * DESCRIPTION: RETURN_AT token in string literal "return@"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "return@"
    return if (op.length == 7 && op.startsWith("return") && op[6] == '@') "OK" else "NOK"
}
