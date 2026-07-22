// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: 1 < 2 and 5 > 3 compare integer literals
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1 < 2 && 5 > 3) "OK" else "NOK"
