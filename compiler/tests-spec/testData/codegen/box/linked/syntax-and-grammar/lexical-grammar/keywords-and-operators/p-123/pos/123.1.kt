// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 1
 * DESCRIPTION: VARARG token in function with vararg parameter
 */
fun join123(vararg parts: String): String = parts.joinToString("")

// TESTCASE NUMBER: 1
fun box(): String = join123("O", "K")
