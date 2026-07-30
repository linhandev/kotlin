// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: destructuring combines with when expression
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun test(p: Pt): Int {
    val (x, y) = p
    return when {
        else -> x + y
    }
}

fun box(): String {
    if (test(Pt(2, 3)) != 5) return "NOK"
    return "OK"
}
