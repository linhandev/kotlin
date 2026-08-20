// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 111 -> sentence 111
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 111 -> sentence 111
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 111 -> sentence 111
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 111 -> sentence 111
 * NUMBER: 1
 * DESCRIPTION: data class secondary constructor delegates to primary without adding component properties type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Point(val x: Int, val y: Int) {
    constructor(x: Int) : this(x, 0)
}

fun case1() {
    val viaSecondary = Point(1)
    viaSecondary checkType { check<Point>() }
    viaSecondary.x checkType { check<Int>() }
    viaSecondary.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPrimary = Point(2, 3)
    viaPrimary checkType { check<Point>() }
    viaPrimary.x checkType { check<Int>() }
    viaPrimary.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaSecondaryOther = Point(5)
    viaSecondaryOther checkType { check<Point>() }
    viaSecondaryOther.x checkType { check<Int>() }
    viaSecondaryOther.y checkType { check<Int>() }
}
