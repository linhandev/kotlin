// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 32 -> sentence 32
 *                 type-system, built-in-integer-types -> paragraph 32 -> sentence 32
 *                expressions, constant-literals, the-types-for-integer-literals -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: Long literal with numeric separator participates in remainder expression
 */

// TESTCASE NUMBER: 1
fun test(): Long = 10_00L % 3L

fun box(): String {
    if (test() != 1L) return "NOK"
    if (1_000L % 7L != 6L) return "NOK"
    if (10_00L % 3L != 1L) return "NOK"
    return "OK"
}
