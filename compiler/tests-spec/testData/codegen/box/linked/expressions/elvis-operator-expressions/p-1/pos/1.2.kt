// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: chained elvis operators evaluate left-to-right for nullability
 */

// TESTCASE NUMBER: 1

fun box(): String = if ((null ?: null ?: 1) == 1) "OK" else "NOK"
