// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                expressions, comparison-expressions -> paragraph 10 -> sentence 10
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: custom Comparable rangeTo/in
 */

// TESTCASE NUMBER: 1
data class Age(val v: Int) : Comparable<Age> {
    override fun compareTo(other: Age): Int = v.compareTo(other.v)
}

fun test(): Boolean = Age(2) in Age(1)..Age(3)

fun box(): String {
    if (!test()) return "NOK"
    if (Age(0) in Age(1)..Age(3)) return "NOK"
    if (Age(1) !in Age(1)..Age(3)) return "NOK"
    if (Age(3) !in Age(1)..Age(3)) return "NOK"
    if (Age(4) in Age(1)..Age(3)) return "NOK"
    return "OK"
}
