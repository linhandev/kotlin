// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 114 -> sentence 114
 * NUMBER: 2
 * DESCRIPTION: INFIX token in infix String concatenation function
 */
infix fun String.and114(other: String): String = this + other

// TESTCASE NUMBER: 1
fun box(): String = "O" and114 "K"
