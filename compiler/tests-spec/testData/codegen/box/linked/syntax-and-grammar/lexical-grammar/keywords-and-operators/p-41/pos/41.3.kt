// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 41 -> sentence 41
 * NUMBER: 3
 * DESCRIPTION: LANGLE token in generic function fun <T> identity(value: T)
 */
// TESTCASE NUMBER: 1

fun <T> id41(value: T): T = value

fun box(): String { check(id41("kw-41-41-3") == "kw-41-41-3"); return "OK" }
