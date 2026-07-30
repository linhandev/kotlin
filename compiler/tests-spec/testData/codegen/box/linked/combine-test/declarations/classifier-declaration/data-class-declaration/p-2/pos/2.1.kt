// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: for-loop destructuring over data class collection uses componentN
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun test(ps: List<Pt>): Int {
    var s = 0
    for ((x, y) in ps) s += x + y
    return s
}

fun box(): String {
    if (test(listOf(Pt(1, 2), Pt(3, 4))) != 10) return "NOK"
    return "OK"
}
