// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 38 -> sentence 38
 * NUMBER: 2
 * DESCRIPTION: AT_BOTH_WS token with multiple file annotations separated by blank lines
 */

@file:JvmName("BoxFile38")

@file:Suppress("WARNING")

fun marker38_3(): Int = 383

// TESTCASE NUMBER: 1
fun box(): String {
    if (marker38_3() != 383) return "NOK"
    return "OK"
}