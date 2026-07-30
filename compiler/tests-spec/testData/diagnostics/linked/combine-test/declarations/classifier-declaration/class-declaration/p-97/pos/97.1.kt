// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 97 -> sentence 97
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 97 -> sentence 97
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 97 -> sentence 97
 * NUMBER: 1
 * DESCRIPTION: no-arg primary constructor can be delegated via this() type inference in class declaration
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
    val viaSecondary = Box(2)
    viaSecondary checkType { check<Box>() }
    viaSecondary.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaImplicitPrimary = Box()
    viaImplicitPrimary checkType { check<Box>() }
    viaImplicitPrimary.v checkType { check<Int>() }
}
