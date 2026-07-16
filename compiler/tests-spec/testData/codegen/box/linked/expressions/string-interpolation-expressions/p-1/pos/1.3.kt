// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: string template result type is kotlin.String
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = 42
    val s: String = "$x"
    return if (s == "42") "OK" else "NOK"
}
