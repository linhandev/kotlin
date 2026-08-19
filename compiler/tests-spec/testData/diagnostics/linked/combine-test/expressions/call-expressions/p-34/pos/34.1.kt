// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 34 -> sentence 34
 *                type-inference, introduction-1 -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: multiple type parameters inferred from value arguments separately
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <K, V> pair(k: K, v: V): Pair<K, V> = k to v

fun <A, B, C> triple(a: A, b: B, c: C): Triple<A, B, C> = Triple(a, b, c)

fun case_1() {
    checkSubtype<Pair<Int, String>>(pair(1, "a"))
    checkSubtype<Pair<String, Double>>(pair("x", 2.0))
    checkSubtype<Triple<Int, String, Boolean>>(triple(1, "b", true))
}
