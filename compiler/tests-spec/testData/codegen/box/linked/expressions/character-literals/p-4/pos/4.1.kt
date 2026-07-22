// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: unicode escape \uFFFF represents maximum BMP codepoint U+FFFF
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val c = '\uFFFF'
    return if (c.toInt() == 0xFFFF) "OK" else "NOK"
}
