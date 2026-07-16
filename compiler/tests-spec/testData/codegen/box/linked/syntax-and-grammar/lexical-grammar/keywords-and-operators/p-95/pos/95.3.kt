// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 3
 * DESCRIPTION: AS token in when branch cast expression
 */
// TESTCASE NUMBER: 1
fun whenCastAs95(value: Any): String {
    return when (value) {
        is Int -> (value as Number).toString()
        is String -> value
        else -> "NOK"
    }
}

fun box(): String = whenCastAs95("OK")
