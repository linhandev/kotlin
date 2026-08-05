// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 27 -> sentence 27
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: negative separator Int literal infers Int and toLong() matches Long literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = -1_000 + 1_000 == 0 && (-1_000).toLong() == -1_000L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(-1_000)
    checkSubtype<Long>(-1_000L)
}

fun case_2_check() {
    checkSubtype<Int>(-1_000)
}

fun case_3_check() {
    checkSubtype<Int>(-1_000 + 1_000)
}
