// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unescaped letter A in character literal evaluates to itself
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c = 'A'
    return if (c == 'A') "OK" else "NOK"
}
