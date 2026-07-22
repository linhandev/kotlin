// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: float literal 1.5f evaluates to 1.5
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Float = 1.5f
    return if (x == 3f / 2f) "OK" else "NOK"
}
