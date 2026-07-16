// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 2
 * DESCRIPTION: FIELD token in constructor parameter @field:Suppress
 */
// TESTCASE NUMBER: 1

class FieldParam57(@field:Suppress("UNUSED_PARAMETER") val id: Int)

fun box(): String {
    return if (FieldParam57(7).id == 7) "OK" else "NOK"
}
