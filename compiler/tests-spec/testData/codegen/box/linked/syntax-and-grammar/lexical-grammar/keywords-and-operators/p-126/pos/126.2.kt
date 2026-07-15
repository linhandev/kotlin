// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 2
 * DESCRIPTION: REIFIED token in inline reified function with type parameter value
 */
inline fun <reified T> default126(value: T): T = value

// TESTCASE NUMBER: 1
fun box(): String = if (default126("codegen-126-2") == "codegen-126-2") "OK" else "NOK"
