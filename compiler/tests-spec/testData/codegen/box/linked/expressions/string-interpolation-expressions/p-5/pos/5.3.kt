// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: single-line string template allows whitespace around code inside interpolation
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = "sum=${1 +
        2}"
    return if (s == "sum=3") "OK" else "NOK"
}
