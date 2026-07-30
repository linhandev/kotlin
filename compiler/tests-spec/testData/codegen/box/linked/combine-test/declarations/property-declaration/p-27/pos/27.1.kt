/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: public override of protected open property
 */

// TESTCASE NUMBER: 1
open class Base {
    protected open val x: Int = 1
}

class Derived : Base() {
    public override val x: Int = 2
}

fun test() = Derived().x

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
