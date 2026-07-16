// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: double literal 5e10 without decimal point evaluates to five billion
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 5e10
    return if (x == 50_000_000_000.0) "OK" else "NOK"
}
