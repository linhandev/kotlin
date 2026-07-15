// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 3
 * DESCRIPTION: REIFIED token in inline reified safe cast function
 */
inline fun <reified T> cast126(value: Any?): T? = value as? T

// TESTCASE NUMBER: 1
fun box(): String = if (cast126<String>("codegen-126-3") ?: "NOK" == "codegen-126-3") "OK" else "NOK"
