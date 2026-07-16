// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 3
 * DESCRIPTION: NOT_IN token with Hidden comment before !in operator
 */
// TESTCASE NUMBER: 1
fun notInWithComment99(value: Int): String {
    return if (value /** hidden */ !in 1..5) "OK" else "NOK"
}

fun box(): String = notInWithComment99(10)
