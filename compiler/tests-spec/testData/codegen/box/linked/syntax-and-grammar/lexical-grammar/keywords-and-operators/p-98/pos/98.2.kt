// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 2
 * DESCRIPTION: NOT_IS token in when branch !is String
 */
// TESTCASE NUMBER: 1
fun whenNotIs98(value: Any): String {
    return when (value) {
        !is String -> "OK"
        else -> "NOK"
    }
}

fun box(): String = whenNotIs98(42)
