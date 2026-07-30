// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: nullable Double compared with Any? using structural equality infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(a: Double?, b: Any?) {
    checkSubtype<Boolean>(a == b)
    checkSubtype<Boolean>(a != b)
    checkSubtype<Boolean>(a == null)
}
