// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: single-quoted character literal has type kotlin.Char
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c: Char = 'A'
    return if (c is Char && c == 'A') "OK" else "NOK"
}
