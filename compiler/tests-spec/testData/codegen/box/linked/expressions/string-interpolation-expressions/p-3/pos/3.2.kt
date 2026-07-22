// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: multiline string does not treat backslash as escape
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = """\n"""
    return if (s == "\\n") "OK" else "NOK"
}
