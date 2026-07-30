/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: binary div resolves to operator fun div
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun div(k: Int): Vec = Vec(x / k)
}

fun test(): Int = (Vec(6) / 2).x

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
