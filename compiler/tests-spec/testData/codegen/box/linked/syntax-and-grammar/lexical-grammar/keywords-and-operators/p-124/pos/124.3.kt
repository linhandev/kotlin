// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 3
 * DESCRIPTION: NOINLINE token in noinline lambda returned from inline function
 */
inline fun store124(noinline supplier: () -> String): () -> String = supplier

// TESTCASE NUMBER: 1
fun box(): String = if (store124 { "codegen-124-3" }() == "codegen-124-3") "OK" else "NOK"
