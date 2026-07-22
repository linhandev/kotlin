// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: float literal 2.5f evaluates to 2.5
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Float = 2.5f
    return if (x == 5f / 2f) "OK" else "NOK"
}
