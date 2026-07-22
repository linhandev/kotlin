// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: > and >= operators perform numeric comparison and yield kotlin.Boolean
 */

// TESTCASE NUMBER: 1

fun box(): String = if (5 > 3 && 2 >= 2) "OK" else "NOK"
