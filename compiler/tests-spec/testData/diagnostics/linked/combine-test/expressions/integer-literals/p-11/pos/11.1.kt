// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 11 -> sentence 11
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Int.MAX_VALUE separator literal is Int and toLong()+1 enters Long range
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 2_147_483_647 == Int.MAX_VALUE && 2_147_483_647.toLong() + 1L == 2_147_483_648L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(2_147_483_647)
    checkSubtype<Long>(2_147_483_648)
}

fun case_2_check() {
    checkSubtype<Int>(2_147_483_647)
}

fun case_3_check() {
    checkSubtype<Int>(2_147_483_647 - 1)
}
