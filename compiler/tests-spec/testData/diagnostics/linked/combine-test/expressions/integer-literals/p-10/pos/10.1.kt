// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 10 -> sentence 10
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: separator literal beyond Int.MAX_VALUE infers Long and Int.MAX_VALUE boundary remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 2_147_483_647 == Int.MAX_VALUE

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(2_147_483_648)
    checkSubtype<Int>(2_147_483_647)
}

fun case_2_check() {
    checkSubtype<Long>(2_147_483_648)
}

fun case_3_check() {
    checkSubtype<Int>(2_147_483_647)
}
