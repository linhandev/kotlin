// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 121 -> sentence 121
 * NUMBER: 4
 * DESCRIPTION: CONST token in const val with compile-time expression
 */
const val CONST_EXPR121: String = "O" + "K"

// TESTCASE NUMBER: 1
fun box(): String = CONST_EXPR121
