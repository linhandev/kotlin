// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: comparison less-or-equal and greater-or-equal with newline around operator
 */

// TESTCASE NUMBER: 1

fun box(): String = if (2 <= 2 && 3
    >= 1) "OK" else "NOK"
