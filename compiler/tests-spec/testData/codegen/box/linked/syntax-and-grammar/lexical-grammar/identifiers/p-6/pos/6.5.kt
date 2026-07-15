// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 6 -> sentence 6
 * NUMBER: 5
 * DESCRIPTION: Soft keyword file used as Identifier in property declaration
 */
// TESTCASE NUMBER: 1
val file = 5

fun box(): String {
    val ok = file == 5
    return if (ok) "OK" else "NOK"
}
