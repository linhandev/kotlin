// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 *                expressions, comparison-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: class member compareTo enables comparison operators via Comparable
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun test(): Boolean = Point(3) < Point(5)

fun box(): String {
    if (!test()) return "NOK"
    if (!(Point(5) > Point(3))) return "NOK"
    if (!(Point(3) <= Point(3))) return "NOK"
    return "OK"
}
