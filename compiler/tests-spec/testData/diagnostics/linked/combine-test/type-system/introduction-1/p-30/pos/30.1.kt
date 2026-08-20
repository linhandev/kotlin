// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 30 -> sentence 30
 *                expressions, equality-expressions -> paragraph 30 -> sentence 30
 *                expressions, cast-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: List equality compares elements after erasure, independent of declared type arguments type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: List<Int> = listOf(1, 2)
    val b: List<Any> = listOf(1, 2)
    checkSubtype<Boolean>(a == b)
    checkSubtype<Boolean>((a as List<*>) == (b as List<*>))
}
