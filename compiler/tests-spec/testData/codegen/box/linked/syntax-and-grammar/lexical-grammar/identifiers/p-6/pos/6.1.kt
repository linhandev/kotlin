// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Note example val field = 2 with field as soft keyword Identifier
 */
// TESTCASE NUMBER: 1
val field = 2

fun box(): String {
    return when (field) { 2 -> "OK"; else -> "NOK" }
}
