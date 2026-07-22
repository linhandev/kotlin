// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: Multiple interpolated fragments concatenate in order
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = "a${1}b${2}c"
    return if (s == "a1b2c") "OK" else "NOK"
}
