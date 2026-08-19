// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: plain class == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(val v: Int)

fun case1() {
    checkSubtype<Boolean>(C(1) == C(1))
}
