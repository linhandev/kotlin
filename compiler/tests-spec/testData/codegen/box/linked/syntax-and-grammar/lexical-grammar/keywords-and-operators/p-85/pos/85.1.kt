// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: TRY token in try-catch statement
 */
// TESTCASE NUMBER: 1
fun tryCatch85(): String {
    try {
        return "codegen-85-1"
    } catch (e: Exception) {
        return "NOK"
    }
}

fun box(): String { val ok = tryCatch85() == "codegen-85-1"; return ok.takeIf { it }?.let { "OK" } ?: "NOK" }
