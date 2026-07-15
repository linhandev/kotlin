#!

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Minimal shebang line (only #!); code after minimal shebang compiles and runs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val hash = '#'
    val bang = '!'
    return if ("$hash$bang" == "#!") "OK" else "NOK"
}
