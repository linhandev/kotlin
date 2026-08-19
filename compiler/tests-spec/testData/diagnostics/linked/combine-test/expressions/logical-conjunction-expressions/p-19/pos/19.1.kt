// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 *                type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, elvis-operator-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: Elvis with && infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean?) {
    checkSubtype<Boolean>(flag ?: false && true)
}
