/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: override val with custom getter
 */

// TESTCASE NUMBER: 1
open class Base {
    open val x: Int = 1
}

class Derived : Base() {
    override val x: Int get() = 2
}

fun test() = Derived().x

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
