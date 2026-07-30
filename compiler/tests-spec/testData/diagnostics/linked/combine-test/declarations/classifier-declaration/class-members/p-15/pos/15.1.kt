// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 *                expressions, comparison-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: class member compareTo via Comparable enables comparison expression inferring Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun case1() {
    checkSubtype<Boolean>(Point(3) < Point(5))
}
