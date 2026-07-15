// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Letter Unicode Lo category in identifier 名字
 */
// TESTCASE NUMBER: 1
val 名字 = 3

fun box(): String = if (名字 == 3) "OK" else "NOK"
