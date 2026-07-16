// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 40 -> sentence 40
 * NUMBER: 2
 * DESCRIPTION: QUEST_NO_WS token in safe call x?.length without Hidden after ?
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val s: String? = "abc"
    return if (s?.length == 3) "OK" else "NOK"
}
