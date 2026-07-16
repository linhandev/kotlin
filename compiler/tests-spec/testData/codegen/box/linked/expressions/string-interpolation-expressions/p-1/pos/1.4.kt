// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: String interpolation result is concatenation of all fragments
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = "world"
    val s = "Hello, $x!"
    return if (s == "Hello, world!") "OK" else "NOK"
}
