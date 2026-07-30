// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 95 -> sentence 95
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body can access primary-initialized member after this() delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Log(val msg: String) {
    var bodyObserved = ""

    constructor(code: Int) : this(code.toString()) {
        bodyObserved = msg
    }
}

fun case1() {
    val viaSecondary = Log(1)
    viaSecondary checkType { check<Log>() }
    viaSecondary.msg checkType { check<String>() }
    viaSecondary.bodyObserved checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPrimary = Log("hi")
    viaPrimary checkType { check<Log>() }
    viaPrimary.msg checkType { check<String>() }
    viaPrimary.bodyObserved checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaAnother = Log(99)
    viaAnother checkType { check<Log>() }
    viaAnother.msg checkType { check<String>() }
    viaAnother.bodyObserved checkType { check<String>() }
}
