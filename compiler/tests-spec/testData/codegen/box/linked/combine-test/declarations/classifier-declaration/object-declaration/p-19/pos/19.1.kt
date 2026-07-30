// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 19 -> sentence 19
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: object can implement an interface by delegation
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

class Impl : I {
    override fun f(): Int = 1
}

object D : I by Impl()

fun test(): Int = D.f()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
