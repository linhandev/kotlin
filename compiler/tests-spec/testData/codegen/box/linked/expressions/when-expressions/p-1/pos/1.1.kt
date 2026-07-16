// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when with subject in parentheses matches subject against branch conditions
 */

// TESTCASE NUMBER: 1

fun box(): String = when (1) { 1 -> "OK"; else -> "NOK" }
