// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 309 -> sentence 309
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 309 -> sentence 309
 *                inheritance, inheriting -> paragraph 309 -> sentence 309
 * NUMBER: 1
 * DESCRIPTION: precise types for inner inheritance chain can still access the outer receiver via this@outer
 * HELPERS: checkType
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

fun case_1() {
    val sub = Outer("ok").Sub()
    sub checkType { check<Outer.Sub>() }
    checkSubtype<Outer.Base>(sub)
    sub.get() checkType { check<String>() }
}
