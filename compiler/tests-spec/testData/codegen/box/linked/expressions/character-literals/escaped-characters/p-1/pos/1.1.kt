// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals, escaped-characters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: character escapes for tab backspace CR LF apostrophe quote backslash and dollar evaluate to Unicode code points
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val escapes = listOf(
        '\t' to '\u0009',
        '\b' to '\u0008',
        '\r' to '\u000D',
        '\n' to '\u000A',
        '\'' to '\u0027',
        '\"' to '\u0022',
        '\\' to '\u005C',
        '\$' to '\u0024',
    )
    return if (escapes.all { (escaped, expected) -> escaped == expected }) "OK" else "NOK"
}
