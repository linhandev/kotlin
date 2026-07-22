// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: double literal 1.5 evaluates to one and a half
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 1.5
    return if (x == 3.0 / 2.0) "OK" else "NOK"
}
