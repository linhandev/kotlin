// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: Block comment with code before and after
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 1 /* comment */ + 2
    return if (x == 3) "OK" else "NOK"
}