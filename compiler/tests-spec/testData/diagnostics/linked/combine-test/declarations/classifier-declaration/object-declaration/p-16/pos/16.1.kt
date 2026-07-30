// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: object can implement a nested interface
 * HELPERS: checkType
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

fun case_1() {
    checkSubtype<Outer.Inner>(Impl)
    checkSubtype<Int>(Impl.f())
}
