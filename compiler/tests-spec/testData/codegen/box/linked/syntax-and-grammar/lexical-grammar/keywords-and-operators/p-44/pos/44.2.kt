// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 44 -> sentence 44
 * NUMBER: 2
 * DESCRIPTION: GE token in when branch when (x >= 0)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "ge-when-44-2"
    val x = 10
    val result = when {
        x >= 0 -> expected
        else -> "NOK"
    }
    if (result != expected) return "NOK"
    return "OK"
}
