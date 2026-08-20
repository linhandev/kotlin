// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 132 -> sentence 132
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 132 -> sentence 132
 *                inheritance, inheriting -> paragraph 132 -> sentence 132
 * NUMBER: 1
 * DESCRIPTION: subclass init block runs after superclass property initializers type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    val s = StringBuilder().apply { append("B") }
}

class Child : Base() {
    init {
        s.append("C")
    }
}

fun case1() {
    val viaChild = Child()
    viaChild checkType { check<Child>() }
    viaChild.s checkType { check<StringBuilder>() }
}

// TESTCASE NUMBER: 2
open class AlphaBase {
    val s = StringBuilder().apply { append("A") }
}

class AlphaChild : AlphaBase() {
    init {
        s.append("1")
    }
}

fun case2() {
    val viaAlpha = AlphaChild()
    viaAlpha checkType { check<AlphaChild>() }
    viaAlpha.s checkType { check<StringBuilder>() }
}

// TESTCASE NUMBER: 3
open class BetaBase {
    val s = StringBuilder().apply { append("X") }
}

class BetaChild : BetaBase() {
    init {
        s.append("Y")
    }
}

fun case3() {
    val viaBeta = BetaChild()
    viaBeta checkType { check<BetaChild>() }
    viaBeta.s checkType { check<StringBuilder>() }
}
