/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 319 -> sentence 319
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 319 -> sentence 319
 * NUMBER: 1
 * DESCRIPTION: outer class method returning an inner instance has the correct type
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner

    fun create(): Inner = Inner()
}

fun test(o: Outer = Outer()): Boolean = o.create() is Outer.Inner

fun box(): String {
    if (!test()) return "NOK: test"
    val o = Outer()
    if (o.create() !is Outer.Inner) return "NOK: create"
    if (Outer().create() !is Outer.Inner) return "NOK: default"
    return "OK"
}
