// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 133 -> sentence 133
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 133 -> sentence 133
 * NUMBER: 1
 * DESCRIPTION: abstract class init block runs when concrete subclass instance is created in class declaration
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

abstract class B {
    init {
        count += 1
    }

    companion object {
        var count = 0
    }
}

class First : B()
class Second : B()

fun flagAfterImpl(): Boolean {
    A.flag = false
    Impl()
    return A.flag
}

fun countAfterTwoInstances(): Int {
    B.count = 0
    First()
    Second()
    return B.count
}

fun box(): String {
    if (!flagAfterImpl()) return "NOK: flag"
    if (countAfterTwoInstances() != 2) return "NOK: count"
    return "OK"
}
