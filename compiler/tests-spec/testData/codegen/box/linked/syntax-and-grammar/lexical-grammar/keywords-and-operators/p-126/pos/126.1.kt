// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 1
 * DESCRIPTION: REIFIED token in inline reified type parameter function
 */
inline fun <reified T> label126(): String = "codegen-126-1"
// TESTCASE NUMBER: 1
fun box(): String = if (label126<String>() == "codegen-126-1") "OK" else "NOK"
