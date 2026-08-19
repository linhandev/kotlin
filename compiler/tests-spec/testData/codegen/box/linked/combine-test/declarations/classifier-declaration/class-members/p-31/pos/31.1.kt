// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 31 -> sentence 31
 *                expressions, comparison-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: compareTo equality yields true for <= and >= on equal points
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun test(): Boolean = Point(5) <= Point(5)

fun box(): String {
    if (!test()) return "NOK: <="
    if (!(Point(5) >= Point(5))) return "NOK: >="
    if (Point(5) < Point(5)) return "NOK: <"
    if (Point(5) > Point(5)) return "NOK: >"
    return "OK"
}
