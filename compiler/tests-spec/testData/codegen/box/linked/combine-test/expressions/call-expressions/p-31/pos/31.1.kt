// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 31 -> sentence 31
 *                type-inference, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: generic type argument inferred from value argument
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun <K, V> pair(k: K, v: V): Pair<K, V> = k to v

fun box(): String {
    if (id(1) != 1) return "NOK"
    if (id("hello") != "hello") return "NOK"
    if (pair(1, "a") != Pair(1, "a")) return "NOK"
    return "OK"
}
