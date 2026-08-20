// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 319 -> sentence 319
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 319 -> sentence 319
 * NUMBER: 1
 * DESCRIPTION: precise types for outer class method returning an inner instance has the correct type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner

    fun create(): Inner = Inner()
}

fun case_1() {
    val inner = Outer().create()
    inner checkType { check<Outer.Inner>() }
    checkSubtype<Outer.Inner>(inner)
}
