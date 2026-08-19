// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                type-system, built-in-integer-types -> paragraph 12 -> sentence 12
 *                type-inference, local-type-inference -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Long multiplicative expression result type is Long
 */

// TESTCASE NUMBER: 1
fun test(): Long {
    val a = 3L
    val b = 4L
    return a * b
}

fun box(): String {
    if (test() != 12L) return "NOK"
    if (run {
            val x = 6L
            val y = 7L
            x * y
        } != 42L) return "NOK"
    if (run {
            val p = -2L
            val q = 5L
            p * q
        } != -10L) return "NOK"
    return "OK"
}
