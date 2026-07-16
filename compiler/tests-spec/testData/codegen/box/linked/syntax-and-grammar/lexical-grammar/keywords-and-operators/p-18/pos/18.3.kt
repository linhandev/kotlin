// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 18 -> sentence 18
 * NUMBER: 3
 * DESCRIPTION: DISJ token used in compound logical expression a || b || c; left-associative || short-circuits on true
 */

// TESTCASE NUMBER: 1
var sideEffect18_3 = false

fun fail18_3(): Boolean {
    sideEffect18_3 = true
    return true
}

fun box(): String {
    sideEffect18_3 = false
    val result = true || fail18_3() || false
    if (!result) return "NOK"
    if (sideEffect18_3) return "NOK"
    return "OK"
}
