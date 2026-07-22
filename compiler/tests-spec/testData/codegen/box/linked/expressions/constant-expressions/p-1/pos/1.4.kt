// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: integer arithmetic on literals is compile-time constant
 */

// TESTCASE NUMBER: 1

const val N: Int = 10 - 3

fun box(): String = if (N == 7) "OK" else "NOK"
