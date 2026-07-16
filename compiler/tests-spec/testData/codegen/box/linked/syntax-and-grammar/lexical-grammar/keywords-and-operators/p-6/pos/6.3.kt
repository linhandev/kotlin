// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: LSQUARE token used in map indexing map["key"]
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val map = mapOf("name" to "Alice", "age" to 30)
    return if (map["name"] == "Alice" && map["age"] == 30) "OK" else "NOK"
}
