// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: half-open range 1..<3 excludes upper bound
 */

// TESTCASE NUMBER: 1

fun box(): String = if (2 in 1..<3 && 3 !in 1..<3) "OK" else "NOK"
