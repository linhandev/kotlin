// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 3
 * DESCRIPTION: QUEST_WS token with block comment as Hidden between quest in String? ?; spaced nullable String holds value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: String? /* hidden */ ? = "393"
    return if (x == "393") "OK" else "NOK"
}
