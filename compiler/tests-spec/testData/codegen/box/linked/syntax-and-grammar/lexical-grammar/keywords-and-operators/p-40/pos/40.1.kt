// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: QUEST_NO_WS token in nullable type Int? without Hidden after ?; nullable Int holds assigned value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Int? = 401
    return if (x == 401) "OK" else "NOK"
}
