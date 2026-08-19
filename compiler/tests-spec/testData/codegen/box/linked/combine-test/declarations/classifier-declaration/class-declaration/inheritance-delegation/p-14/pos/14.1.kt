/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: class delegation with inherited interface implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

open class Base : I {
    override fun foo() = "base"
}

class Derived(i: I) : I by i

fun test() = Derived(Base()).foo()

fun box(): String {
    if (test() != "base") return "NOK"
    return "OK"
}
