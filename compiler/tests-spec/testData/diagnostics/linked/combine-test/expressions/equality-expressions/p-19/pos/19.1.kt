// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Any? equals null infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Any?) {
    checkSubtype<Boolean>(x == null)
}
