// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 1
 * DESCRIPTION: WHEN token in when expression without subject
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val v = when {
        false -> "bad"
        else -> "kw-84-84-1"
    }
    return if (v == "kw-84-84-1") "OK" else "NOK"
}
