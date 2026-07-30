// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 133 -> sentence 133
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 133 -> sentence 133
 * NUMBER: 1
 * DESCRIPTION: abstract class init block runs when concrete subclass instance is created type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class A {
    init {
        flag = true
    }

    companion object {
        var flag = false
    }
}

class Impl : A()

fun case1() {
    val instance = Impl()
    instance checkType { check<Impl>() }
    A.flag checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 2
abstract class B {
    init {
        count += 1
    }

    companion object {
        var count = 0
    }
}

class First : B()

fun case2() {
    val first = First()
    first checkType { check<First>() }
    B.count checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class Second : B()

fun case3() {
    val second = Second()
    second checkType { check<Second>() }
    B.count checkType { check<Int>() }
}
