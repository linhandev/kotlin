// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: float literal 1.5e2f evaluates to 150.0
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1.5e2f == 150f) "OK" else "NOK"
