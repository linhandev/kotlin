// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: multiplicative binds tighter than additive in mixed expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun plus(o: Vec): Vec = Vec(x + o.x)
    operator fun times(k: Int): Vec = Vec(x * k)
}

fun case_1(): Int = (Vec(1) + Vec(2) * 3).x

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
