// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: single-line string template embeds computed arithmetic value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if ("sum=${1 + 2}" == "sum=3") "OK" else "NOK"
}
