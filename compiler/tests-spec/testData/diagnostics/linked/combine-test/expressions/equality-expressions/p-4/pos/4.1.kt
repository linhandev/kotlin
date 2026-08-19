// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: two nullables equality infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(a: String?, b: String?) {
    checkSubtype<Boolean>(a == b)
}
