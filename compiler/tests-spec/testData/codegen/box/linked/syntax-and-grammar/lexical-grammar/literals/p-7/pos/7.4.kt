// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 * NUMBER: 4
 * DESCRIPTION: FloatLiteral with omitted whole part and f suffix .25f
 */
// TESTCASE NUMBER: 1
fun box(): String = if (.25f == 0.25f) "OK" else "NOK"
