// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: double literal 1.0e10 uses lowercase e exponent notation
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1.0e10 == 10_000_000_000.0) "OK" else "NOK"
