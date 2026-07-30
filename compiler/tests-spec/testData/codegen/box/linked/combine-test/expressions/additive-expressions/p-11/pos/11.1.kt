/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: plusAssign desugars to plus when only plus is defined, a.x becomes 3
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun plus(o: Vec): Vec = Vec(x + o.x)
}

fun test(): Int {
    var a = Vec(1)
    a += Vec(2)
    return a.x
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
