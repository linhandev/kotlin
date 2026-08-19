/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: extension operator fun plus participates in additive expression
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int)

operator fun Vec.plus(o: Vec): Vec = Vec(x + o.x)

fun test(): Vec = Vec(1) + Vec(2)

fun box(): String {
    if (test().x != 3) return "NOK"
    return "OK"
}
