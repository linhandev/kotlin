// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: const val N = 42 accepts compile-time constant integer
 */

// TESTCASE NUMBER: 1

const val N: Int = 42

fun box(): String = if (N == 42) "OK" else "NOK"
