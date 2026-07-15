// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 3
 * DESCRIPTION: WHERE token with multiple type parameter constraints
 */
// TESTCASE NUMBER: 1

fun <K, V> pairKey81(map: Map<K, V>): K where K : Comparable<K>, V : Any = map.keys.first()

fun box(): String {
    val expected = "where-81"
    if (pairKey81(mapOf(expected to 42)) != expected) return "NOK"
    return "OK"
}
