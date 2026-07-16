// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: equality value operators == and != connect comparisons
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1.toInt() == 1 && 1 != 2) "OK" else "NOK"
