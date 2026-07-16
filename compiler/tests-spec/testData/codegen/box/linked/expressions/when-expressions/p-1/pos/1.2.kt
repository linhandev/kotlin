// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: when { true -> "OK" } without subject matches true branch
 */

// TESTCASE NUMBER: 1

fun box(): String = when { true -> "OK"; else -> "NOK" }
