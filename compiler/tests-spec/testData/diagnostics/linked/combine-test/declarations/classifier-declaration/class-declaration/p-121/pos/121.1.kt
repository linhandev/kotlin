// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 121 -> sentence 121
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 121 -> sentence 121
 * NUMBER: 1
 * DESCRIPTION: init block may assign uninitialized val property exactly once type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Scaled(val seed: Int) {
    val out: Int

    init {
        out = seed * 2
    }
}

fun case1() {
    val fromThree = Scaled(3)
    fromThree checkType { check<Scaled>() }
    fromThree.seed checkType { check<Int>() }
    fromThree.out checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val fromFive = Scaled(5)
    fromFive checkType { check<Scaled>() }
    fromFive.seed checkType { check<Int>() }
    fromFive.out checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val fromZero = Scaled(0)
    fromZero checkType { check<Scaled>() }
    fromZero.seed checkType { check<Int>() }
    fromZero.out checkType { check<Int>() }
}
