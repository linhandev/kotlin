// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: unsuffixed floating-point literal 1.5 has type kotlin.Double
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Double = 1.5
    return if (x is Double && x == 1.5) "OK" else "NOK"
}
