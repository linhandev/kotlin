// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 1
 * DESCRIPTION: CLASS token in simple class declaration class C
 */
// TESTCASE NUMBER: 1

class Holder67(val value: String)

fun box(): String {
    val expected = "class-67"
    if (Holder67(expected).value != expected) return "NOK"
    return "OK"
}
