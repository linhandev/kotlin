// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: multiline string template with $id embeds simple identifier value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val n = 5
    val s = """
value=$n
"""
    return if (s == "\nvalue=5\n") "OK" else "NOK"
}
