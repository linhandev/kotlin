// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: data class generates component1/component2 for destructuring
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun test(p: Pt): Int {
    val (a, b) = p
    return a + b
}

fun box(): String {
    if (test(Pt(1, 2)) != 3) return "NOK"
    return "OK"
}
