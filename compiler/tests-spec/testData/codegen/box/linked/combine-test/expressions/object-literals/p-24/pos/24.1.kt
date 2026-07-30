
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: object literal can implement nested interface
 */

// TESTCASE NUMBER: 1
class Outer {
    interface Inner {
        fun f(): Int
    }
}

fun test(): Int = object : Outer.Inner {
    override fun f(): Int = 9
}.f()

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}
