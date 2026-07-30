/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: method calling delegated foo still uses delegated implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Impl : I {
    override fun foo() = "impl"
}

class Delegate(i: I) : I by i {
    fun bar() = foo()
}

fun test() = Delegate(Impl()).bar()

fun box(): String {
    if (test() != "impl") return "NOK"
    return "OK"
}
