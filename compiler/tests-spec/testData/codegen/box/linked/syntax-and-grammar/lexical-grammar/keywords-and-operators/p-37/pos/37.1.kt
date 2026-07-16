// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: AT_PRE_WS token with blank line before @Suppress annotation
 */

@Suppress("UNUSED_VARIABLE")
// TESTCASE NUMBER: 1
fun box(): String {
    val unused = 1
    return "OK"
}
