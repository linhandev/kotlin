// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: "value=$x" with x=42 interpolates to "value=42"
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 42
    return if ("value=$x" == "value=42") "OK" else "NOK"
}
