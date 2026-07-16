// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: multiline string template embeds computed value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x = "abc"
    val s = """
value=${x.length}
"""
    return if (s == "\nvalue=3\n") "OK" else "NOK"
}
