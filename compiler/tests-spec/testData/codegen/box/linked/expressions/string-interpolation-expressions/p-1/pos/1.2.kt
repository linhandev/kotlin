// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: multiline string literal without interpolation preserves line breaks
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s = """text"""
    return if (s == "text") "OK" else "NOK"
}
