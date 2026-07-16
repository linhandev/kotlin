// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: != and !== operators compare for inequality
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1 != 2 && "a" !== "b") "OK" else "NOK"
