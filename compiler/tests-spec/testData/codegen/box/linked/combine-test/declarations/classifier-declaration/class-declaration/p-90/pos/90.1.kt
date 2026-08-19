// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 90 -> sentence 90
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 90 -> sentence 90
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegates to empty primary then assigns in body
 */

// TESTCASE NUMBER: 1
class Box() {
    var v = 0
    constructor(x: Int) : this() {
        v = x
    }
}

fun test(): Int = Box(3).v

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
