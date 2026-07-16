// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 96 -> sentence 96
 * NUMBER: 2
 * DESCRIPTION: IS token in when branch type check
 */
// TESTCASE NUMBER: 1
fun whenIs96(value: Any): String {
    return when (value) {
        is String -> value
        else -> "NOK"
    }
}

fun box(): String = whenIs96("OK")
