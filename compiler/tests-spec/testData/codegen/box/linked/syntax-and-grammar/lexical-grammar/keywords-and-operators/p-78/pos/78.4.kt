// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 4
 * DESCRIPTION: THIS token in lambda with explicit this@Outer label
 */
// TESTCASE NUMBER: 1

class LambdaThis78 {
    val token = "kw-78-78-4"

    fun read(): String {
        val getter: () -> String = { this@LambdaThis78.token }
        return getter()
    }
}

fun box(): String {
    val expected = "kw-78-78-4"
    val result = LambdaThis78().read()
    if (result != expected) return "NOK"
    return "OK"
}
