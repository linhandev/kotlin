// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 13 -> sentence 13
 * NUMBER: 2
 * DESCRIPTION: ADD token used in operator overloading a.plus(b); member plus returns expected sum
 */

// TESTCASE NUMBER: 1
class Add13(private val value: Int) {
    fun plus(other: Int): Int = value + other
}

fun box(): String {
    val result = Add13(10).plus(3)
    return if (result == 13) "OK" else "NOK"
}
