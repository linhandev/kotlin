// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 121 -> sentence 121
 * NUMBER: 1
 * DESCRIPTION: CONST token in top-level const val declaration
 */
const val CONST_TOP121: String = "codegen-121-1"
// TESTCASE NUMBER: 1
fun box(): String = if (CONST_TOP121 == "codegen-121-1") "OK" else "NOK"
