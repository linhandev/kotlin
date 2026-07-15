// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: Soft keyword field used as Identifier without escaping
 */
// TESTCASE NUMBER: 1
val field = 3

fun box(): String {
    return if (field == 3) "OK" else "NOK"
}
