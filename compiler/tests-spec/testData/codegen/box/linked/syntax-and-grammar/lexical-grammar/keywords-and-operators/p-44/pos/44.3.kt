// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 44 -> sentence 44
 * NUMBER: 3
 * DESCRIPTION: GE token in filter predicate it >= 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = listOf(1, 2, 3, 4).filter { it >= 2 }
    return if (result == listOf(2, 3, 4)) "OK" else "NOK"
}
