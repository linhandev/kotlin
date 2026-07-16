// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: == and != result type is kotlin.Boolean
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Boolean = 1.toInt() == 1
    return if (x) "OK" else "NOK"
}
