// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: single-line string with escape sequence \t produces tab character
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = "a\tb"
    return if (s == "a\tb") "OK" else "NOK"
}
