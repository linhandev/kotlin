// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 4
 * DESCRIPTION: CATCH token in try-catch expression
 */
fun catchExpr85(): String = try {
    "OK"
} catch (_: Exception) {
    "NOK"
}

// TESTCASE NUMBER: 1
fun box(): String = catchExpr85()
