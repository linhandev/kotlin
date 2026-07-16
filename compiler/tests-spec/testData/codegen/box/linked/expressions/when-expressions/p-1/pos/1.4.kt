// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: when (true) { true -> 1; else -> 2L } has Number type with value 1
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Number = when (true) { true -> 1; else -> 2L }
    return if (x == 1) "OK" else "NOK"
}
