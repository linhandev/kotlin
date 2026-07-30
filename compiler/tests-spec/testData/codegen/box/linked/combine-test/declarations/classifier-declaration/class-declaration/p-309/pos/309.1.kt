/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 309 -> sentence 309
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 309 -> sentence 309
 *                inheritance, inheriting -> paragraph 309 -> sentence 309
 * NUMBER: 1
 * DESCRIPTION: inner inheritance chain can still access the outer receiver via this@Outer
 */

// TESTCASE NUMBER: 1
class Outer(val s: String) {
    inner open class Base {
        fun t(): String = this@Outer.s
    }

    inner class Sub : Base() {
        fun get(): String = t()
    }
}

fun test(): String = Outer("ok").Sub().get()

fun box(): String {
    if (Outer("ok").Sub().get() != "ok") return "NOK: get"
    if (test() != "ok") return "NOK: test"
    if (Outer("x").Sub().t() != "x") return "NOK: t"
    return "OK"
}
