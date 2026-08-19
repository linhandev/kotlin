// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 8 -> sentence 8
 *                type-system, introduction-1 -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: null === null infers Boolean with SENSELESS_COMPARISON
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(<!SENSELESS_COMPARISON!>null === null<!>)
}
