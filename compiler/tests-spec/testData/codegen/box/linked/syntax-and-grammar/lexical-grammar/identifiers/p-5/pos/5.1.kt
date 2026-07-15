// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Soft keyword receiver used as Identifier without escaping
 */
// TESTCASE NUMBER: 1
val receiver = 1

fun box(): String {
    return if (receiver + 0 == 1) "OK" else "NOK"
}
