// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: plus return type participates in subsequent expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class A(val v: Int) {
    operator fun plus(b: B): C = C(v + b.v)
}

data class B(val v: Int)
data class C(val v: Int)

fun case_1(): Int = (A(1) + B(2)).v

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
