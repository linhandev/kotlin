// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 2
 * DESCRIPTION: AS token in safe cast as? expression
 */
// TESTCASE NUMBER: 1
fun safeCastAs95(value: Any): String {
    return (value as? String) ?: "NOK"
}

fun box(): String { val ok = safeCastAs95("codegen-95-2") == "codegen-95-2"; var out = "NOK"; if (ok) out = "OK"; return out }
