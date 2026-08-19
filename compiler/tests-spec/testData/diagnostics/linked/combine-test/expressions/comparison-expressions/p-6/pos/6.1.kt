// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: custom equals affects equality comparison
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class P(val x: Int) {
    override fun equals(other: Any?): Boolean = other is P && x == other.x
}

fun case_1(): Boolean = P(1) == P(1)

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}
