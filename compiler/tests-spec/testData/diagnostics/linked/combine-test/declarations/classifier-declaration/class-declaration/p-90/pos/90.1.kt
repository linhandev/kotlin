// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 90 -> sentence 90
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 90 -> sentence 90
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegates to empty primary then assigns body property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box() {
    var v = 0
    constructor(x: Int) : this() {
        v = x
    }
}

fun case1() {
    checkSubtype<Int>(Box(3).v)
}
