// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: NaN == and equals infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(Double.NaN == Double.NaN)
    checkSubtype<Boolean>(Double.NaN.equals(Double.NaN))
}
