// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 4
 * DESCRIPTION: IF token in if expression used as statement side effect
 */
// TESTCASE NUMBER: 1

fun box(): String {
    if (if (true) true else false) return "OK"
    return "NOK"
}
