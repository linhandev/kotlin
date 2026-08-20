// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 306 -> sentence 306
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 306 -> sentence 306
 * NUMBER: 1
 * DESCRIPTION: precise types for outer class type parameter is visible inside an inner class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer<T>(val t: T) {
    inner class Inner {
        fun get(): T = t
    }
}

fun case_1() {
    Outer(1).Inner().get() checkType { check<Int>() }
    checkSubtype<Int>(Outer(1).Inner().get())
}
