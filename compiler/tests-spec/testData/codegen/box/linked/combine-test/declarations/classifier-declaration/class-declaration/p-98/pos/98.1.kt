// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 98 -> sentence 98
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 98 -> sentence 98
 *                inheritance, inheriting -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: subclass secondary constructor delegates to superclass primary constructor via super() in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base {
    constructor(v: Int) : super(v)
}

fun viaThree(): Int = Child(3).x

fun viaFive(): Int = Child(5).x

fun box(): String {
    if (viaThree() != 3) return "NOK: three"
    if (viaFive() != 5) return "NOK: five"
    return "OK"
}
