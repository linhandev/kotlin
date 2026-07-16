// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: float literal with f or F suffix has type kotlin.Float
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Float = 1.5f
    return if (x is Float && x == 1.5f) "OK" else "NOK"
}
