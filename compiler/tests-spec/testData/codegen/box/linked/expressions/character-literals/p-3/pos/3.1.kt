// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: escape \n in character literal equals line feed U+000A
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if ('\n' == '\u000A') "OK" else "NOK"
}
