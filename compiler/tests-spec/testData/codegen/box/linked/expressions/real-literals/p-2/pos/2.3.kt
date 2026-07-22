// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: float literal 0.5F with uppercase suffix evaluates to 0.5
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Float = 0.5F
    return if (x == 1f / 2f) "OK" else "NOK"
}
