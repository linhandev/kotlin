// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 5
 * DESCRIPTION: AT_NO_WS token in use-site target annotation @param:Suppress
 */
// TESTCASE NUMBER: 1

fun helper(@Suppress("UNUSED_PARAMETER") x: Int): Int = x

fun box(): String {
    return if (helper(21) == 21) "OK" else "NOK"
}
