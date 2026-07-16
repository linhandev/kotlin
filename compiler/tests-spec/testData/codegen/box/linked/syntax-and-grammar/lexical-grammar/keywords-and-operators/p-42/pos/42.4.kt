// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 42 -> sentence 42
 * NUMBER: 4
 * DESCRIPTION: RANGLE token in nested generic Array<List<String>>
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "rangle-42-4"
    val nested: Array<List<String>> = arrayOf(listOf(expected))
    if (nested[0].single() != expected) return "NOK"
    return "OK"
}
