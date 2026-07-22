// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: '\u0041' character escape evaluates to letter A
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c = '\u0041'
    return if (c == 'A') "OK" else "NOK"
}
