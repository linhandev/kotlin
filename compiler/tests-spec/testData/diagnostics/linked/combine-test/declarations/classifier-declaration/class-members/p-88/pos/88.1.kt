// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 88 -> sentence 88
 * NUMBER: 1
 * DESCRIPTION: custom equals makes == true for equal properties
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val x: Int) {
    override fun equals(other: Any?): Boolean = other is Box && x == other.x
}

fun case1() {
    checkSubtype<Boolean>(Box(42) == Box(42))
}
