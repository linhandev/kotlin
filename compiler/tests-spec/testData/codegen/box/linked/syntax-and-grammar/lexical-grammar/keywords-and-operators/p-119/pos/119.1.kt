// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: FINAL token in final class declaration
 */
final class FinalHolder119(val value: String)

// TESTCASE NUMBER: 1
fun box(): String = if (FinalHolder119("codegen-119-1").value == "codegen-119-1") "OK" else "NOK"
