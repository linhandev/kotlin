// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: data class componentN supports lambda destructuring
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun test(xs: List<Pt>): Int = xs.sumOf { (x, y) -> x + y }

fun box(): String {
    if (test(listOf(Pt(1, 2), Pt(3, 4))) != 10) return "NOK"
    return "OK"
}
