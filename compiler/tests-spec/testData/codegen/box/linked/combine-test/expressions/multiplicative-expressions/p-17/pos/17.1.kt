// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                type-system, built-in-integer-types -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun times(Long) participates in multiplication
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Long): W = W(v * k)
}

fun test(): Long = (W(2L) * 3L).v

fun box(): String {
    if (test() != 6L) return "NOK"
    if ((W(3L) * 4L).v != 12L) return "NOK"
    if ((W(-2L) * 5L).v != -10L) return "NOK"
    return "OK"
}
