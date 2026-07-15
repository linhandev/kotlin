// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: Soft keyword property used as Identifier val property = 1
 */
// TESTCASE NUMBER: 1
val property = 1

fun box(): String {
    property.also { }.let { return if (it == 1) "OK" else "NOK" }
}
