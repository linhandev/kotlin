// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 17 -> sentence 17
 * NUMBER: 3
 * DESCRIPTION: CONJ token used in compound logical expression a && b && c; left-associative && short-circuits on false
 */

// TESTCASE NUMBER: 1
var sideEffect17_3 = false

fun fail17_3(): Boolean {
    sideEffect17_3 = true
    return true
}

fun box(): String {
    sideEffect17_3 = false
    val result = false && fail17_3() && true
    if (result) return "NOK"
    if (sideEffect17_3) return "NOK"
    return "OK"
}
