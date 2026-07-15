// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 2
 * DESCRIPTION: ELSE token in if-else statement with block branches
 */
// TESTCASE NUMBER: 1

fun box(): String {
    var v = "NOK"
    if (false) v = "bad" else v = "kw-83-83-2"
    return if (v == "kw-83-83-2") "OK" else "NOK"
}
