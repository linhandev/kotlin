// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 4
 * DESCRIPTION: TYPEOF token as backtick-escaped function parameter name
 */

fun echoTypeOf80(`typeof`: String): String = `typeof`

// TESTCASE NUMBER: 1
fun box(): String {
    return echoTypeOf80("OK")
}
