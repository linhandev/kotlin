// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 5
 * DESCRIPTION: AS_SAFE token in string literal "as?"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "as?"
    if (!op.endsWith("?")) return "NOK"
    return if (op.startsWith("as")) "OK" else "NOK"
}
