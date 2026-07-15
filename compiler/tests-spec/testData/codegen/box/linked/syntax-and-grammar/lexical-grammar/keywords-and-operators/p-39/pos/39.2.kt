// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 2
 * DESCRIPTION: QUEST_WS token in double nullable Int? ? with Hidden between quest tokens; spaced double-nullable accepts null
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Int? ? = null
    return if (x == null) "OK" else "NOK"
}
