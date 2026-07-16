// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 3
 * DESCRIPTION: ELSE token in if-else if-else chain expression
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val v = if (false) "bad" else if (true) "kw-83-83-3" else "NOK"
    return if (v == "kw-83-83-3") "OK" else "NOK"
}
