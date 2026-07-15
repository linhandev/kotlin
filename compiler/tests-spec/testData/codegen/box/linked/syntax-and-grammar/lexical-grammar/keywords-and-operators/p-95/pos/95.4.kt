// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 4
 * DESCRIPTION: AS token in nested cast chain
 */
// TESTCASE NUMBER: 1
fun nestedCastAs95(value: Any?): String {
    val first = value as Any
    return first as String
}

fun box(): String { val ok = nestedCastAs95("codegen-95-4") == "codegen-95-4"; check(ok); return "OK" }
