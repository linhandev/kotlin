// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 31 -> sentence 31
 *                expressions, comparison-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: compareTo equality via <= infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun case1() {
    checkSubtype<Boolean>(Point(5) <= Point(5))
}
