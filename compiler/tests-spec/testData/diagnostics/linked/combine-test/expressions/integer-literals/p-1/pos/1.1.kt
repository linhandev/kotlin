// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 1 -> sentence 1
 *                expressions, additive-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: decimal Int literal infers Int type and toLong() matches Long literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 42.toLong() == 42L && 42 + 1 == 43

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(42)
    checkSubtype<Long>(42L)
}

fun case_2_check() {
    checkSubtype<Int>(42)
}

fun case_3_check() {
    checkSubtype<Int>(2_147_483_647)
}
