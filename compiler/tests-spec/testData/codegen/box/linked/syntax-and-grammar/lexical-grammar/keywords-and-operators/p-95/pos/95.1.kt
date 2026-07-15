// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: AS token in unsafe cast expression
 */
// TESTCASE NUMBER: 1
fun castAs95(value: Any): String {
    return value as String
}

fun box(): String { if (castAs95("codegen-95-1") == "codegen-95-1") { return "OK" }; return "NOK" }
