// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 35 -> sentence 35
 *                type-inference, introduction-1 -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: multiple type parameters can be partially or fully explicitly specified
 */

// TESTCASE NUMBER: 1
fun <K, V> pair(k: K, v: V): Pair<K, V> = k to v

fun <A, B, C> triple(a: A, b: B, c: C): Triple<A, B, C> = Triple(a, b, c)

fun box(): String {
    if (pair<Int, String>(1, "a") != Pair(1, "a")) return "NOK"
    if (pair<String, Double>("x", 2.0) != Pair("x", 2.0)) return "NOK"
    if (triple<Int, String, Boolean>(1, "b", true) != Triple(1, "b", true)) return "NOK"
    return "OK"
}
