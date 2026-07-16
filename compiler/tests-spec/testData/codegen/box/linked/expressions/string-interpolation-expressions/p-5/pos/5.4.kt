// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: Interpolated null reference converts to string null
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: String? = null
    return if ("${x}" == "null") "OK" else "NOK"
}
