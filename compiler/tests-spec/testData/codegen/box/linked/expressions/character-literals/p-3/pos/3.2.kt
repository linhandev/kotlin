// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: escape \' in character literal equals apostrophe U+0027
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if ('\'' == '\u0027') "OK" else "NOK"
}
