// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 98 -> sentence 98
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 98 -> sentence 98
 *                inheritance, inheriting -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: subclass secondary constructor delegates to superclass primary constructor via super() type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base {
    constructor(v: Int) : super(v)
}

fun case1() {
    val child = Child(3)
    child checkType { check<Child>() }
    child.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val child: Base = Child(5)
    checkSubtype<Base>(child)
}
