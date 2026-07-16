// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 2
 * DESCRIPTION: WHEN token in when expression with subject
 */
// TESTCASE NUMBER: 1

fun mapWhen84(value: Int): String = when (value) {
    42 -> "kw-84-84-2"
    else -> "NOK"
}

fun box(): String {
    return if (mapWhen84(42) == "kw-84-84-2") "OK" else "NOK"
}
