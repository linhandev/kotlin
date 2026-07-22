// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 5
 * DESCRIPTION: Interpolated value uses kotlin.Any.toString conversion
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if ("${42}" == "42") "OK" else "NOK"
}
