// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: double literal 1e10 evaluates to ten billion
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 1e10
    return if (x == 10_000_000_000.0) "OK" else "NOK"
}
