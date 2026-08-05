// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 22 -> sentence 22
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 22 -> sentence 22
 *                expressions, call-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: separator Long literal infers Long type when passed to Long parameter in function call
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun id(x: Long): Long = x

fun case_1(): Long = id(1_000L)

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2_check() {
    checkSubtype<Long>(id(2_000L))
}

fun case_3_check() {
    checkSubtype<Long>(id(1_000L) + id(500L))
}
