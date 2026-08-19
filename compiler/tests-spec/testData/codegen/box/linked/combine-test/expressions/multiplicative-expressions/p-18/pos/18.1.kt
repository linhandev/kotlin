// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                type-system, built-in-integer-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun rem(Long) participates in remainder
 */

// TESTCASE NUMBER: 1
data class N(val v: Long) {
    operator fun rem(m: Long): N = N(v % m)
}

fun test(): Long = (N(10L) % 3L).v

fun box(): String {
    if (test() != 1L) return "NOK"
    if ((N(7L) % 3L).v != 1L) return "NOK"
    if ((N(10L) % 4L).v != 2L) return "NOK"
    return "OK"
}
