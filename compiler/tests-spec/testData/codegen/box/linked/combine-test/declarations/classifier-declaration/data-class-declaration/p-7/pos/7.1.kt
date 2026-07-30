// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: underscore skips a destructuring component
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun test(p: Pt): Int {
    val (x, _) = p
    return x
}

fun box(): String {
    if (test(Pt(5, 9)) != 5) return "NOK"
    return "OK"
}
