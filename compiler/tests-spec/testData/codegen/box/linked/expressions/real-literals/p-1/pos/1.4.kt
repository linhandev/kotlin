// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: float literal 42F evaluates to 42.0
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Float = 42F
    return if (x == 42.0f) "OK" else "NOK"
}
