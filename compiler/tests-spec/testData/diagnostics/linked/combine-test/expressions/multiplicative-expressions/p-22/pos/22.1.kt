// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 22 -> sentence 22
 *                type-system, built-in-integer-types -> paragraph 22 -> sentence 22
 *                declarations, function-declaration, extension-function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: extension operator fun Long.times(W) type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class W(val v: Long)

operator fun Long.times(w: W): W = W(this * w.v)

fun case_1(): W = 4L * W(5L)

fun case_1_check() {
    checkSubtype<W>(case_1())
}

fun case_2(): Long = (4L * W(5L)).v

fun case_2_check() {
    checkSubtype<Long>(case_2())
}
