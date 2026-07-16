// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 4
 * DESCRIPTION: WHERE token in generic class with multiple type parameter constraints
 */
// TESTCASE NUMBER: 1

class Pair81<A, B>(val first: A, val second: B) where A : Number, B : CharSequence

fun box(): String {
    val expected = "kw-81-81-4"
    val result = Pair81(1, expected).second.toString()
    if (result != expected) return "NOK"
    return "OK"
}
