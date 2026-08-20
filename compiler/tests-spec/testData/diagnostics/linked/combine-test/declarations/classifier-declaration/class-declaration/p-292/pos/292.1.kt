// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 292 -> sentence 292
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 292 -> sentence 292
 * NUMBER: 1
 * DESCRIPTION: precise types for nested class can be constructed without an outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested(val v: Int)
}

fun case_1() {
    val nested = Outer.Nested(1)
    nested checkType { check<Outer.Nested>() }
    nested.v checkType { check<Int>() }
    checkSubtype<Int>(Outer.Nested(1).v)
}
