// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 102 -> sentence 102
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: abstract class secondary constructor delegates to primary constructor and concrete subclass uses super delegation in class declaration
 */

// TESTCASE NUMBER: 1
abstract class A(val x: Int) {
    constructor() : this(0)
}

class Impl : A {
    constructor() : super(0)
    constructor(v: Int) : super(v)
}

fun viaDefault(): Int = Impl().x

fun viaValue(): Int = Impl(7).x

fun box(): String {
    if (viaDefault() != 0) return "NOK: default x"
    if (viaValue() != 7) return "NOK: value x"
    return "OK"
}
