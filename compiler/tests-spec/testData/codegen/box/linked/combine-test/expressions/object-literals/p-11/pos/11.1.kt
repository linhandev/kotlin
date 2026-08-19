
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: object literal can override default interface implementation
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int = 1
}

fun test(): Int = object : I {
    override fun f(): Int = 2
}.f()

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
