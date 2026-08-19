// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 21 -> sentence 21
 *                declarations, classifier-declaration, class-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: nested data class still generates componentN
 */

// TESTCASE NUMBER: 1
class Outer {
    data class Inner(val v: Int)
}

fun test(): Int {
    val (x) = Outer.Inner(1)
    return x
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
