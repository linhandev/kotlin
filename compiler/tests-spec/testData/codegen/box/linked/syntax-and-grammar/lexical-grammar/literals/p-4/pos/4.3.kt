// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: DecDigits as fraction part in double literal 0.456
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0.456 == 0.456) "OK" else "NOK"
