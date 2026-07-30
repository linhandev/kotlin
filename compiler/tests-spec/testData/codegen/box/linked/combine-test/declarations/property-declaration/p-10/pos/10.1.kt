/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: protected property accessible via subclass method
 */

// TESTCASE NUMBER: 1
open class Base {
    protected val x: Int = 42
}

class Derived : Base() {
    fun readX() = x
}

fun test() = Derived().readX()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
