/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: basic interface implementation by class delegation
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Impl : I {
    override fun foo() = "impl"
}

class Delegate(i: I) : I by i

fun test() = Delegate(Impl()).foo()

fun box(): String {
    if (test() != "impl") return "NOK"
    return "OK"
}
