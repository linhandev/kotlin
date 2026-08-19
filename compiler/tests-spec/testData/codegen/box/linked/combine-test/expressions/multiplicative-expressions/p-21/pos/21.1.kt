// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                type-system, built-in-integer-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: operator times result Long continues in built-in Long addition
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Long): W = W(v * k)
}

fun test(): Long = (W(2L) * 3L).v + 1L

fun box(): String {
    if (test() != 7L) return "NOK"
    if ((W(3L) * 4L).v + 2L != 14L) return "NOK"
    if ((W(-2L) * 5L).v + 10L != 0L) return "NOK"
    return "OK"
}
