// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 305 -> sentence 305
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 305 -> sentence 305
 * NUMBER: 1
 * DESCRIPTION: KClass distinguishes nested class from inner class
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested
    inner class Inner
}

fun test(): Boolean = Outer.Nested::class != Outer.Inner::class

fun box(): String {
    if (Outer.Nested::class == Outer.Inner::class) return "NOK: equal"
    if (!test()) return "NOK: test"
    if (Outer.Nested::class.simpleName != "Nested") return "NOK: nested-name"
    if (Outer.Inner::class.simpleName != "Inner") return "NOK: inner-name"
    return "OK"
}
