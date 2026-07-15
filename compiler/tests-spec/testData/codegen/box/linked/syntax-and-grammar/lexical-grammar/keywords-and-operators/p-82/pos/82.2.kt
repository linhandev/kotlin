// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 2
 * DESCRIPTION: IF token in if statement with block body
 */
// TESTCASE NUMBER: 1

fun box(): String {
    var v = "NOK"
    if (true) { v = "kw-82-82-2" }
    return if (v == "kw-82-82-2") "OK" else "NOK"
}
