// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: Array contentEquals and == infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(arrayOf(1, 2).contentEquals(arrayOf(1, 2)))
    checkSubtype<Boolean>(arrayOf(1, 2) == arrayOf(1, 2))
}
