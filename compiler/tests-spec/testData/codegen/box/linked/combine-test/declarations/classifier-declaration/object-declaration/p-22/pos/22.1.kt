// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 22 -> sentence 22
 *                declarations, classifier-declaration, class-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: nested object inside a class can implement an interface
 */

// TESTCASE NUMBER: 1
interface Cache {
    fun get(): Int
}

class Outer {
    object Store : Cache {
        override fun get(): Int = 1
    }
}

fun test(): Int = Outer.Store.get()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
