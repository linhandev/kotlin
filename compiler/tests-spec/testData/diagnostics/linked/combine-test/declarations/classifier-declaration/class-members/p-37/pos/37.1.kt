// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 37 -> sentence 37
 *                expressions, comparison-expressions -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: sorted via Comparable compareTo infers List
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun case1() {
    checkSubtype<List<Point>>(listOf(Point(3), Point(1), Point(2)).sorted())
}
