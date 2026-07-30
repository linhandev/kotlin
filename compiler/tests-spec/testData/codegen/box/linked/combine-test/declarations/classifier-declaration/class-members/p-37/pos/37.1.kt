// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 37 -> sentence 37
 *                expressions, comparison-expressions -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: Comparable compareTo enables sorted order by x
 */

// TESTCASE NUMBER: 1
class Point(val x: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = x.compareTo(other.x)
}

fun test(): List<Point> = listOf(Point(3), Point(1), Point(2)).sorted()

fun box(): String {
    val sorted = test()
    if (sorted.map { it.x } != listOf(1, 2, 3)) return "NOK"
    return "OK"
}
