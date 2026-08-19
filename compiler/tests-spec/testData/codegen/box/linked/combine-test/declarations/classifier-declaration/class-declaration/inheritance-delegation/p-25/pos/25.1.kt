// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: override open lazy delegated property in Derived
 */

// TESTCASE NUMBER: 1
open class Base {
    open val x: Int by lazy { 1 }
}

class Derived : Base() {
    override val x: Int by lazy { 2 }
}

fun test() = Derived().x

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
