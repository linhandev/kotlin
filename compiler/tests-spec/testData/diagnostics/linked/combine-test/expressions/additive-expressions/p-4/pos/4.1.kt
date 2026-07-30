// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: binary minus resolves to operator fun minus
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun minus(o: Vec): Vec = Vec(x - o.x)
}

fun case_1(): Vec = Vec(3) - Vec(1)

fun case_1_check() {
    checkSubtype<Vec>(case_1())
}
