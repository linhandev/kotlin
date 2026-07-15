// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 2
 * DESCRIPTION: TRY token in try-finally statement
 */
// TESTCASE NUMBER: 1
fun tryFinally85(): String {
    val expected = "try-85-2"
    var result = "NOK"
    try {
        result = expected
    } finally {
        check(result == expected)
    }
    return result
}

fun box(): String {
    val expected = "try-85-2"
    if (tryFinally85() != expected) return "NOK"
    return "OK"
}
