// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: object inherits interface default method implementations
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int = 1
}

object O : I

fun test(): Int = O.f()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
