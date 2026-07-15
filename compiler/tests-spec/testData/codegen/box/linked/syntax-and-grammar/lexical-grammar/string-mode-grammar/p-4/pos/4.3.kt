// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: QUOTE_CLOSE string returned from function
 */
// TESTCASE NUMBER: 1
fun message(): String = "closed"

fun box(): String { val step1 = message() == "closed"; val step2 = step1; return if (step2) "OK" else "NOK" }
