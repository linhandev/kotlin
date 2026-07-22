// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: null ?: 1 evaluates to 1 when left side is null
 */

// TESTCASE NUMBER: 1

fun box(): String = if ((null ?: 1) == 1) "OK" else "NOK"
