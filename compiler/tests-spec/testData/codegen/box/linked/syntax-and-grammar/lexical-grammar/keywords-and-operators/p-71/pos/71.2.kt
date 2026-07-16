// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 2
 * DESCRIPTION: VAL token in class val property and primary constructor val parameter
 */
// TESTCASE NUMBER: 1

class ValHolder71(val token: String) {
    val label: String = token
}

fun box(): String {
    val expected = "val-71"
    if (ValHolder71(expected).label != expected) return "NOK"
    return "OK"
}
