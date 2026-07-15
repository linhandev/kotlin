// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: GET token in property getter get() = expression form
 */
// TESTCASE NUMBER: 1

val prop59: Int get() = 59

fun box(): String { return if (prop59 != 59) "NOK" else "OK" }
