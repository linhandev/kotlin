// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 14 -> sentence 14
 * NUMBER: 3
 * DESCRIPTION: SUB token used in unary minus (negation) -a
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = -5
    val result = -a
    return if (result == 5) "OK" else "NOK"
}
