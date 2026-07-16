// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 4
 * DESCRIPTION: WHEN token in when expression with multiple branches and type test
 */
// TESTCASE NUMBER: 1

fun pick84(x: Int): String = when (x) {
    1 -> "kw-84-84-4"
    2 -> "kw-84-84-4"
    else -> "NOK"
}

fun box(): String {
    if (pick84(1) != "kw-84-84-4") return "NOK"
    return if (pick84(2) == "kw-84-84-4") "OK" else "NOK"
}
