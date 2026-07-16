// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: QUEST_WS token in nullable type Int ? with Hidden before quest; spaced nullable type holds non-null Int
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Int ? = 391
    return if (x == 391) "OK" else "NOK"
}
