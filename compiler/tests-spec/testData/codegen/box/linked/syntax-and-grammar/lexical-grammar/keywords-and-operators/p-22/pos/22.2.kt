// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 22 -> sentence 22
 * NUMBER: 2
 * DESCRIPTION: SEMICOLON token used as empty for-loop body for (i in 1..2);
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    for (i in 1..2);
    count = 2
    return if (count == 2) "OK" else "NOK"
}
