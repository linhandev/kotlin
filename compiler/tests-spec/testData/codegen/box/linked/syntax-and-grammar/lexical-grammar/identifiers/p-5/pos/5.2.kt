// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: Soft keyword delegate used as Identifier without escaping
 */
// TESTCASE NUMBER: 1
val delegate = 2

fun box(): String {
    return if (delegate == 2) "OK" else "NOK"
}
