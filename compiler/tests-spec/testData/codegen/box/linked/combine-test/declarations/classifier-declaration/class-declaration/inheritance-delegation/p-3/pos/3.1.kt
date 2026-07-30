/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: override after class delegation returns delegate implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Impl : I {
    override fun foo() = "impl"
}

class Delegate(i: I) : I by i {
    override fun foo() = "delegate"
}

fun test() = Delegate(Impl()).foo()

fun box(): String {
    if (test() != "delegate") return "NOK"
    return "OK"
}
