// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: custom equals == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(val v: Int) {
    override fun equals(other: Any?): Boolean = other is C && v == other.v
}

fun case1() {
    checkSubtype<Boolean>(C(1) == C(1))
}
