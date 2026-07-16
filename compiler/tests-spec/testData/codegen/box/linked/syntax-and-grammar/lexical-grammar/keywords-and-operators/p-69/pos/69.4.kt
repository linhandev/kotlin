// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 4
 * DESCRIPTION: FUN token in local function inside box with runtime check
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "local-69"
    fun local69(): String = expected
    if (local69() != expected) return "NOK"
    return "OK"
}
