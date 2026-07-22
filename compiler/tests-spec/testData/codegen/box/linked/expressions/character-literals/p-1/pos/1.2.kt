// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: unescaped symbol # is allowed in character literal
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c = '#'
    return if (c == '#') "OK" else "NOK"
}
