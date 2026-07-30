// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 313 -> sentence 313
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 313 -> sentence 313
 * NUMBER: 1
 * DESCRIPTION: precise types for companion object can declare a nested class accessible via outer.nested
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        class Nested(val v: Int)
    }
}

fun case_1() {
    Outer.Companion.Nested(1).v checkType { check<Int>() }
    checkSubtype<Int>(Outer.Companion.Nested(1).v)
}
