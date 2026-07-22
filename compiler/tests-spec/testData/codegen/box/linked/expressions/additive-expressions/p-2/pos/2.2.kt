// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, additive-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: minus operator resolves to overloadable operator fun minus
 */

// TESTCASE NUMBER: 1

fun box(): String = if (5 - 2 == 3) "OK" else "NOK"
