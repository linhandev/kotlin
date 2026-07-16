// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 3
 * DESCRIPTION: DATA token in data class with destructuring
 */
data class Pair109(val first: String, val second: Int)

// TESTCASE NUMBER: 1
fun box(): String {
    val (token, count) = Pair109("OK", 1)
    return if (count == 1) token else "NOK"
}
