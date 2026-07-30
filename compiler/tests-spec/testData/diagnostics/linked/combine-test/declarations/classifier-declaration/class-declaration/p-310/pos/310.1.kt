// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 310 -> sentence 310
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 310 -> sentence 310
 *                inheritance, inheriting -> paragraph 310 -> sentence 310
 * NUMBER: 1
 * DESCRIPTION: precise types for non-inner nested class inheritance does not introduce an outer receiver
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    open class Base

    class Sub : Base()
}

fun case_1() {
    val sub = Outer.Sub()
    sub checkType { check<Outer.Sub>() }
    checkSubtype<Outer.Base>(sub)
}
