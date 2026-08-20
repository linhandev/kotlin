// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 105 -> sentence 105
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 105 -> sentence 105
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: init block runs before secondary constructor body after this() delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Order() {
    val steps = mutableListOf<String>()

    init {
        steps += "init"
    }

    constructor(tag: String) : this() {
        steps += "sec"
    }
}

fun case1() {
    val viaSecondary = Order("t")
    viaSecondary checkType { check<Order>() }
    viaSecondary.steps checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPrimary = Order()
    viaPrimary checkType { check<Order>() }
    viaPrimary.steps checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaSecondaryOther = Order("u")
    viaSecondaryOther checkType { check<Order>() }
    viaSecondaryOther.steps checkType { check<MutableList<String>>() }
}
