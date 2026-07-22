// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: multiline string literal allows raw newline in content
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = """
line1
line2
"""
    return if (s == "\nline1\nline2\n") "OK" else "NOK"
}
