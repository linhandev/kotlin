// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 20 -> sentence 20
 *                inheritance, inheriting -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: object must explicitly override conflicting interface default members
 */

// TESTCASE NUMBER: 1
interface A {
    fun f(): Int = 1
}

interface B {
    fun f(): Int = 2
}

object O : A, B {
    override fun f(): Int = super<A>.f() + super<B>.f()
}

fun test(): Int = O.f()

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
