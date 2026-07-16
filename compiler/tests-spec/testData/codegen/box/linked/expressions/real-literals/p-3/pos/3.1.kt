// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: double literal 1.5e10 evaluates to fifteen billion
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 1.5e10
    return if (x == 15_000_000_000.0) "OK" else "NOK"
}
