
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: object literal inherits default interface implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int = 1
}

fun test(): Int = object : I {}.f()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
