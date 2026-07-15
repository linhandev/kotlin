// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 4
 * DESCRIPTION: NOT_IN token distinguished from EXCL ! in same function
 */
// TESTCASE NUMBER: 1
fun exclVsNotIn99(flag: Boolean, value: Int): String {
    if (!flag) return "NOK"
    return if (value !in 1..3) "OK" else "NOK"
}

fun box(): String = exclVsNotIn99(true, 99)
