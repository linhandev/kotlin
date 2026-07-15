// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 4
 * DESCRIPTION: IN token in for-in over map entries
 */
// TESTCASE NUMBER: 1
fun mapForIn97(): String {
    val expected = "in-map-97-4"
    val data = mapOf("key" to expected)
    for ((_, value) in data) {
        if (value == expected) return value
    }
    return "NOK"
}

fun box(): String {
    val expected = "in-map-97-4"
    if (mapForIn97() != expected) return "NOK"
    return "OK"
}
