/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 310 -> sentence 310
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 310 -> sentence 310
 *                inheritance, inheriting -> paragraph 310 -> sentence 310
 * NUMBER: 1
 * DESCRIPTION: non-inner nested class inheritance does not introduce an outer receiver
 */

// TESTCASE NUMBER: 1
class Outer {
    open class Base

    class Sub : Base()
}

fun test(): Outer.Sub = Outer.Sub()

fun box(): String {
    val sub = Outer.Sub()
    if (sub !is Outer.Base) return "NOK: base"
    if (test() !is Outer.Sub) return "NOK: test"
    return "OK"
}
