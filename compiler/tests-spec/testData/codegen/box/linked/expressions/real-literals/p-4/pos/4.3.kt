// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: double literal 3.14e-2 uses negative exponent and evaluates to 0.0314
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 3.14e-2
    return if (x == 0.0314) "OK" else "NOK"
}
