/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: chained plus is left-associative, result x is 6
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun plus(o: Vec): Vec = Vec(x + o.x)
}

fun test(): Int = (Vec(1) + Vec(2) + Vec(3)).x

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
