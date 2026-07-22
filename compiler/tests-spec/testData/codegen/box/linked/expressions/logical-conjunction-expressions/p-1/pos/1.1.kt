// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, logical-conjunction-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: true && true evaluates to true
 */

// TESTCASE NUMBER: 1

fun box(): String = if (true && true) "OK" else "NOK"
