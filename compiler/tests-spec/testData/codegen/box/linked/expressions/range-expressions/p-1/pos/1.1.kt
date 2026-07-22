// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: closed range 1..3 creates range including both endpoints
 */

// TESTCASE NUMBER: 1

fun box(): String = if (2 in 1..3) "OK" else "NOK"
