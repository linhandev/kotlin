/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: class delegation with data class interface implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

data class Impl(val name: String) : I {
    override fun foo() = name
}

class Delegate(i: I) : I by i

fun test() = Delegate(Impl("data")).foo()

fun box(): String {
    if (test() != "data") return "NOK"
    return "OK"
}
