// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 4
 * DESCRIPTION: ELSE token in nested if-else expression
 */
// TESTCASE NUMBER: 1

fun nestedElse83(a: Boolean, b: Boolean): String {
    return if (a) {
        "A"
    } else {
        if (b) "kw-83-83-4" else "NOK"
    }
}

fun box(): String {
    val expected = "kw-83-83-4"
    val result = nestedElse83(false, true)
    if (result != expected) return "NOK"
    return "OK"
}
