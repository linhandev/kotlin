// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 * NUMBER: 3
 * DESCRIPTION: FloatLiteral with uppercase F suffix 0.5F
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0.5F == 0.5f) "OK" else "NOK"
