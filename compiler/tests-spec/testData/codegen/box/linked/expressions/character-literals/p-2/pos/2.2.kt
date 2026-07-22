// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: character literal escape \t evaluates to tab character U+0009
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c = '\t'
    return if (c == '\u0009') "OK" else "NOK"
}
