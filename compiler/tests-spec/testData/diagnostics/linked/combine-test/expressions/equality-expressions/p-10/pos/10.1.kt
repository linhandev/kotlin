// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: == and !== together infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class P(val x: Int)

fun case1() {
    checkSubtype<Boolean>(P(1) == P(1) && P(1) !== P(1))
}
