// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 105 -> sentence 105
 * NUMBER: 2
 * DESCRIPTION: INTERNAL token in internal top-level function declaration
 */
internal fun internalFn105(): String = "codegen-105-2"
// TESTCASE NUMBER: 1
fun box(): String = if (internalFn105() == "codegen-105-2") "OK" else "NOK"
