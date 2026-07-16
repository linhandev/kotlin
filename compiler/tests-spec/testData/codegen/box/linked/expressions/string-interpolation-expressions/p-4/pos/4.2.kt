// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: single-line string template plain text fragment is preserved
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = "prefix-text-suffix"
    return if (s == "prefix-text-suffix") "OK" else "NOK"
}
