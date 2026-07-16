// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 40 -> sentence 40
 * NUMBER: 3
 * DESCRIPTION: QUEST_NO_WS token in elvis operator x ?: default without Hidden after ?
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Int? = null
    val y = x ?: 42
    return if (y == 42) "OK" else "NOK"
}
