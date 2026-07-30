// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 298 -> sentence 298
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 298 -> sentence 298
 * NUMBER: 1
 * DESCRIPTION: precise types for nested class construction does not require any outer instance to exist
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer(val id: Int) {
    class Nested
}

fun case_1() {
    val nested = Outer.Nested()
    nested checkType { check<Outer.Nested>() }
}
