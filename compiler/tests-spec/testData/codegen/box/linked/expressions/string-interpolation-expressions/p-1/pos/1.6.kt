// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: string template concatenates literal parts and interpolated values
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 1
    val s = "prefix-${x}-suffix"
    return if (s == "prefix-1-suffix") "OK" else "NOK"
}
