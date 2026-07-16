// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: multiline string embeds literal dollar via escaped interpolation
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = """${'$'}"""
    return if (s == "$") "OK" else "NOK"
}
