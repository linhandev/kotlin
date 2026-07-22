// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, additive-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: plus operator resolves to overloadable operator fun plus
 */

// TESTCASE NUMBER: 1

fun box(): String = if (1 + 2 == 3) "OK" else "NOK"
