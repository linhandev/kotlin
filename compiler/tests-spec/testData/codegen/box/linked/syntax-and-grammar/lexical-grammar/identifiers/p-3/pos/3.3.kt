// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: UnicodeDigit after Letter and underscore in foo_१_bar
 */
// TESTCASE NUMBER: 1
val foo_१_bar = 3

fun box(): String = if (foo_१_bar == 3) "OK" else "NOK"
