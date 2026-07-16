// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 41 -> sentence 41
 * NUMBER: 4
 * DESCRIPTION: LANGLE token in nested generic Map<String, List<Int>>
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val map: Map<String, List<Int>> = mapOf("key" to listOf(1))
    return if (map["key"]?.single() == 1) "OK" else "NOK"
}
