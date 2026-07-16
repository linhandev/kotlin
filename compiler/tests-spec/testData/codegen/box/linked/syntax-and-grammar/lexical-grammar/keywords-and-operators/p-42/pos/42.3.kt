// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 42 -> sentence 42
 * NUMBER: 3
 * DESCRIPTION: RANGLE token in filter predicate it > 0
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = listOf(-1, 0, 1, 2).filter { it > 0 }
    return if (result == listOf(1, 2)) "OK" else "NOK"
}
