// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 5
 * DESCRIPTION: QUEST_WS token with newline as Hidden between quest in Boolean? ?; newline-hidden spaced nullable holds Boolean
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Boolean?
        ? = true
    return if (x == true) "OK" else "NOK"
}
