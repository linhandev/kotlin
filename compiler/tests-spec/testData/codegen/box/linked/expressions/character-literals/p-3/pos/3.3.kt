// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: escape \\ in character literal equals backslash U+005C
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if ('\\' == '\u005C') "OK" else "NOK"
}
