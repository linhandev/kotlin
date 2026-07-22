// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, reference-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: === and !== result type is kotlin.Boolean
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Boolean = Any() !== Any()
    return if (x) "OK" else "NOK"
}
