/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: binary times resolves to operator fun times
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun times(k: Int): Vec = Vec(x * k)
}

fun test(): Vec = Vec(2) * 3

fun box(): String {
    if (test().x != 6) return "NOK"
    return "OK"
}
