// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// WITH_STDLIB
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 305 -> sentence 305
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 305 -> sentence 305
 * NUMBER: 1
 * DESCRIPTION: precise types for kclass distinguishes nested class from inner class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested
    inner class Inner
}

fun case_1() {
    checkSubtype<Boolean>(Outer.Nested::class != Outer.Inner::class)
    Outer.Nested::class checkType { check<kotlin.reflect.KClass<Outer.Nested>>() }
    Outer.Inner::class checkType { check<kotlin.reflect.KClass<Outer.Inner>>() }
}
