// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 1
 * DESCRIPTION: VAR token in top-level var property with mutation
 */

var counter72 = 0

// TESTCASE NUMBER: 1
fun box(): String {
    counter72 = 1
    return if (counter72 == 1) "OK" else "NOK"
}
