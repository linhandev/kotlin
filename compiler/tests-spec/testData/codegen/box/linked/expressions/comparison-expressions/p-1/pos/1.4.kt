// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: ordering comparison result type is kotlin.Boolean
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Boolean = 1 < 2
    return if (x) "OK" else "NOK"
}
