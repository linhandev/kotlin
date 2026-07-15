// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 2
 * DESCRIPTION: WHERE token in generic class declaration with constraint
 */
// TESTCASE NUMBER: 1

class Box81<T>(val value: T) where T : CharSequence

fun box(): String {
    val expected = "kw-81-81-2"
    val result = Box81(expected).value.toString()
    if (result != expected) return "NOK"
    return "OK"
}
