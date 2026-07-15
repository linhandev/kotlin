// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 31 -> sentence 31
 * NUMBER: 4
 * DESCRIPTION: RANGE token used with variable bounds start..end
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val start = 2
    val end = 4
    val range = start..end
    return if (range.toList() == listOf(2, 3, 4)) "OK" else "NOK"
}
