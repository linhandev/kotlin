// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 3
 * DESCRIPTION: AT_NO_WS token in @SinceKotlin annotation without space after @
 */

@SinceKotlin("1.0")
fun compute35_3(): Int = 353

// TESTCASE NUMBER: 1
fun box(): String {
    if (compute35_3() != 353) return "NOK"
    return "OK"
}