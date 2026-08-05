// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                type-system, built-in-integer-types -> paragraph 12 -> sentence 12
 *                type-inference, local-type-inference -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Long local variables multiplicative expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long {
    val a = 3L
    val b = 4L
    return a * b
}

fun case_1_check() {
    val a = 3L
    val b = 4L
    checkSubtype<Long>(a * b)
}
