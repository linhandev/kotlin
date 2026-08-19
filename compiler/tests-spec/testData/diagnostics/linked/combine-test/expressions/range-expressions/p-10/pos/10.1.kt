// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                expressions, comparison-expressions -> paragraph 10 -> sentence 10
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: custom Comparable ClosedRange in infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Age(val v: Int) : Comparable<Age> {
    override fun compareTo(other: Age): Int = v.compareTo(other.v)
}

fun case1() {
    checkSubtype<Boolean>(Age(2) in Age(1)..Age(3))
}
