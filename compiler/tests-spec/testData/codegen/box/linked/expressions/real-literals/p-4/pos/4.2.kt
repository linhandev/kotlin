// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: double literal 2.5E+20 uses uppercase E with explicit plus exponent
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 2.5E+20
    return if (x == 2.5e20) "OK" else "NOK"
}
