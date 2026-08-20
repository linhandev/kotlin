// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 102 -> sentence 102
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: abstract class secondary constructor delegates to primary constructor and concrete subclass uses super delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class A(val x: Int) {
    constructor() : this(0)
}

class Impl : A {
    constructor() : super(0)
    constructor(v: Int) : super(v)
}

fun case1() {
    val viaDefault = Impl()
    viaDefault checkType { check<Impl>() }
    viaDefault.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaValue = Impl(5)
    viaValue checkType { check<Impl>() }
    viaValue.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val asAbstract: A = Impl(3)
    checkSubtype<A>(asAbstract)
    asAbstract.x checkType { check<Int>() }
}
