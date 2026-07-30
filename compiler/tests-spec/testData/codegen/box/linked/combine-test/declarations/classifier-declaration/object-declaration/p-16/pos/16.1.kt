// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: object can implement a nested interface
 */

// TESTCASE NUMBER: 1
class Outer {
    interface Inner {
        fun f(): Int
    }
}

object Impl : Outer.Inner {
    override fun f(): Int = 9
}

fun test(): Int = Impl.f()

fun box(): String {
    if (test() != 9) return "NOK: test"
    val i: Outer.Inner = Impl
    if (i.f() != 9) return "NOK: typed"
    return "OK"
}
