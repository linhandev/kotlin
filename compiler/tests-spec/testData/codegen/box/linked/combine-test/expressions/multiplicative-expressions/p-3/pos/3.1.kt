/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: percent operator resolves to operator fun rem
 */

// TESTCASE NUMBER: 1
data class N(val v: Int) {
    operator fun rem(m: Int): N = N(v % m)
}

fun test(): Int = (N(7) % 3).v

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
