// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                type-system, built-in-integer-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun rem(Long) result participates in built-in Long addition
 */

// TESTCASE NUMBER: 1
data class N(val v: Long) {
    operator fun rem(m: Long): N = N(v % m)
}

fun test(): Long = (N(20L) % 7L).v + 1L

fun box(): String {
    if (test() != 7L) return "NOK"
    if ((N(17L) % 5L).v + 2L != 4L) return "NOK"
    if ((N(-7L) % 3L).v + 1L != 0L) return "NOK"
    return "OK"
}
