// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 43 -> sentence 43
 * NUMBER: 2
 * DESCRIPTION: LE token in when branch when (x <= 10)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "le-when-43-2"
    val x = 5
    val result = when {
        x <= 10 -> expected
        else -> "NOK"
    }
    if (result != expected) return "NOK"
    return "OK"
}
