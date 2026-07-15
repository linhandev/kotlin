// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 12 -> sentence 12
 * NUMBER: 2
 * DESCRIPTION: MultiLineStrText standalone dollar not starting template
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""price is $5
ok""" == "price is $5\nok") "OK" else "NOK"
