/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: custom type plus operator resolves to operator fun plus
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun plus(o: Vec): Vec = Vec(x + o.x)
}

fun test(): Vec = Vec(1) + Vec(2)

fun box(): String {
    if (test().x != 3) return "NOK"
    return "OK"
}
