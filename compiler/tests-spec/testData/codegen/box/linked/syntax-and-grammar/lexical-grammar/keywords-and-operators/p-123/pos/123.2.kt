// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 2
 * DESCRIPTION: VARARG token in vararg Int sum function
 */
// TESTCASE NUMBER: 1
fun sum123(vararg values: Int): Int = values.sum()

fun box(): String { return when { sum123(40, 2) == 42 -> "OK"; else -> "NOK" } }
