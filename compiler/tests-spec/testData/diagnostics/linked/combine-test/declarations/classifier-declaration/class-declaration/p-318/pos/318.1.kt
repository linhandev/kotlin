// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 318 -> sentence 318
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 318 -> sentence 318
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 318 -> sentence 318
 * NUMBER: 1
 * DESCRIPTION: precise types for nested enum class can be qualified with the outer class name
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    enum class Mode { ON, OFF }
}

fun case_1() {
    Outer.Mode.ON checkType { check<Outer.Mode>() }
    checkSubtype<Boolean>(Outer.Mode.ON == Outer.Mode.ON)
}
