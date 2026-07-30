// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: override val as var
 */

// TESTCASE NUMBER: 1
open class Base {
    open val x: Int = 1
}

class Derived : Base() {
    override var x: Int = 2
}

fun test() = Derived().apply { x = 3 }.x

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
