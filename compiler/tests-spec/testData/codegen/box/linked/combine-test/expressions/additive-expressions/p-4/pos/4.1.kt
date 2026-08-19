/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: binary minus resolves to operator fun minus
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun minus(o: Vec): Vec = Vec(x - o.x)
}

fun test(): Vec = Vec(3) - Vec(1)

fun box(): String {
    if (test().x != 2) return "NOK"
    return "OK"
}
