// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 118 -> sentence 118
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 118 -> sentence 118
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 118 -> sentence 118
 * NUMBER: 1
 * DESCRIPTION: init block may read already-initialized properties type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Demo(val x: Int) {
    val y = x + 1

    init {
        check(y > 0)
    }
}

fun case1() {
    val fromOne = Demo(1)
    fromOne checkType { check<Demo>() }
    fromOne.x checkType { check<Int>() }
    fromOne.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val fromFive = Demo(5)
    fromFive checkType { check<Demo>() }
    fromFive.x checkType { check<Int>() }
    fromFive.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val fromNine = Demo(9)
    fromNine checkType { check<Demo>() }
    fromNine.x checkType { check<Int>() }
    fromNine.y checkType { check<Int>() }
}
