// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 1
 * DESCRIPTION: NOT_IN token in range negation check x !in 1..10
 */
// TESTCASE NUMBER: 1
fun notInCheck99(value: Int): String {
    return if (value !in 1..10) "OK" else "NOK"
}

fun box(): String = notInCheck99(42)
