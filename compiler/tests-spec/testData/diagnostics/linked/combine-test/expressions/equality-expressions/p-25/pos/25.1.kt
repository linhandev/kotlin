// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: IntArray == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(intArrayOf(1) == intArrayOf(1))
}
