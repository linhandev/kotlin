// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: extension operator fun plus participates in additive expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int)

operator fun Vec.plus(o: Vec): Vec = Vec(x + o.x)

fun case_1(): Vec = Vec(1) + Vec(2)

fun case_1_check() {
    checkSubtype<Vec>(case_1())
}
