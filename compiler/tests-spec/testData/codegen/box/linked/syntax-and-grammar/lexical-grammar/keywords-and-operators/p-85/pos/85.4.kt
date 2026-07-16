// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 4
 * DESCRIPTION: TRY token in try expression form
 */
fun tryExpr85(): String = try {
    "OK"
} catch (_: Exception) {
    "NOK"
}

// TESTCASE NUMBER: 1
fun box(): String = tryExpr85()
