// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 115 -> sentence 115
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 115 -> sentence 115
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 115 -> sentence 115
 *                statements, code-blocks -> paragraph 115 -> sentence 115
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body may execute arbitrary statements with side effects after this() delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Holder(var v: Int) {
    constructor(x: Int, inc: Boolean) : this(x) {
        if (inc) v++
    }
}

fun case1() {
    val viaIncrement = Holder(1, true)
    viaIncrement checkType { check<Holder>() }
    viaIncrement.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaNoIncrement = Holder(1, false)
    viaNoIncrement checkType { check<Holder>() }
    viaNoIncrement.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaPrimary = Holder(5)
    viaPrimary checkType { check<Holder>() }
    viaPrimary.v checkType { check<Int>() }
}
