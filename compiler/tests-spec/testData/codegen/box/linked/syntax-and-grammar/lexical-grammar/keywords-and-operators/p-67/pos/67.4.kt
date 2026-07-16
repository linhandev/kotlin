// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 4
 * DESCRIPTION: CLASS token in generic class declaration
 */
// TESTCASE NUMBER: 1

class Generic67<T>(val payload: T)

fun box(): String {
    val expected = "generic-67"
    if (Generic67(expected).payload != expected) return "NOK"
    return "OK"
}
