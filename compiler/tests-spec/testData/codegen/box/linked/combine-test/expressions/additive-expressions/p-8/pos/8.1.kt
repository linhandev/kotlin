/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: plus return type participates in subsequent expression, result v is 3
 */

// TESTCASE NUMBER: 1
data class A(val v: Int) {
    operator fun plus(b: B): C = C(v + b.v)
}

data class B(val v: Int)
data class C(val v: Int)

fun test(): Int = (A(1) + B(2)).v

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
