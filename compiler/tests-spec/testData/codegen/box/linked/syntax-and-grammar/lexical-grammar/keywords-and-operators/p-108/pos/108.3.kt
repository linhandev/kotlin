// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 108 -> sentence 108
 * NUMBER: 3
 * DESCRIPTION: ANNOTATION token in annotation applied to function
 */
// TESTCASE NUMBER: 1
annotation class FnTag108

@FnTag108
fun taggedFn108(): String = "codegen-108-3"
fun box(): String = if (taggedFn108() == "codegen-108-3") "OK" else "NOK"
