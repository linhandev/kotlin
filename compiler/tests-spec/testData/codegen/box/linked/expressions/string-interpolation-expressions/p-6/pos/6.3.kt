// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: multiline string template can embed double quote character
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = """
say "hi"
"""
    return if (s.contains("\"hi\"")) "OK" else "NOK"
}
