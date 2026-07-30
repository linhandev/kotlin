// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 31 -> sentence 31
 *                type-inference, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: generic type argument inferred from value argument type inference check
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun <K, V> pair(k: K, v: V): Pair<K, V> = k to v

fun case_1() {
    checkSubtype<Int>(id(1))
    checkSubtype<String>(id("hello"))
    checkSubtype<Pair<Int, String>>(pair(1, "a"))
}
