// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: unicode escape supports codepoints from U+0000 through U+FFFF
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val min = '\u0000'
    val max = '\uFFFF'
    return if (min.toInt() == 0 && max.toInt() == 0xFFFF) "OK" else "NOK"
}
