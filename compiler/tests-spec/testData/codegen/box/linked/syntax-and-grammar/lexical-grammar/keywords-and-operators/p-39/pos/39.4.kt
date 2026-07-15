// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 4
 * DESCRIPTION: QUEST_WS token in nullable parenthesized type (Int) ? with Hidden before quest; parenthesized nullable holds value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: (Int) ? = 394
    return if (x == 394) "OK" else "NOK"
}
