// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: single-line string literal without interpolation evaluates to given text
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = "hello"
    return if (s == "hello") "OK" else "NOK"
}
