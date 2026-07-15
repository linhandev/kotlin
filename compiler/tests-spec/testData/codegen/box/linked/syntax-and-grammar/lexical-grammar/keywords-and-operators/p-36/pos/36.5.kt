// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 5
 * DESCRIPTION: AT_POST_WS token in return@label from inline lambda
 */

inline fun runLabeled(block: () -> String): String = block()

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "label-36"
    val result = runLabeled {
        return@runLabeled expected
    }
    if (result != expected) return "NOK"
    return "OK"
}
