// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 19 -> sentence 19
 *                type-system, built-in-integer-types -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun div(Long) participates in division
 */

// TESTCASE NUMBER: 1
data class N(val v: Long) {
    operator fun div(m: Long): N = N(v / m)
}

fun test(): Long = (N(10L) / 4L).v

fun box(): String {
    if (test() != 2L) return "NOK"
    if ((N(7L) / 2L).v != 3L) return "NOK"
    if ((N(-7L) / 2L).v != -3L) return "NOK"
    return "OK"
}
