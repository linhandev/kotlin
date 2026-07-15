// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 4
 * DESCRIPTION: VAR token in local var inside box function
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "var-72-4"
    var local72 = "NOK"
    local72 = expected
    if (local72 != expected) return "NOK"
    return "OK"
}
