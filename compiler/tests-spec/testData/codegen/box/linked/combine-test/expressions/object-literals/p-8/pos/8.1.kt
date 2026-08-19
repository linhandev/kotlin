
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: object literal can override open member
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
}

fun test(): Int = object : Base() {
    override fun f(): Int = 2
}.f()

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
