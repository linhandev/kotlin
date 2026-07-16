// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 50 -> sentence 50
 * NUMBER: 3
 * DESCRIPTION: SINGLE_QUOTE token in when branch character match
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "quote-when-50-3"
    val c = 'z'
    val result = when (c) {
        'z' -> expected
        else -> "NOK"
    }
    if (result != expected) return "NOK"
    return "OK"
}
