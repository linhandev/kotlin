// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 2
 * DESCRIPTION: NOT_IN token in when branch !in range
 */
// TESTCASE NUMBER: 1
fun whenNotIn99(value: Int): String {
    return when (value) {
        !in 1..3 -> "OK"
        else -> "NOK"
    }
}

fun box(): String = whenNotIn99(99)
