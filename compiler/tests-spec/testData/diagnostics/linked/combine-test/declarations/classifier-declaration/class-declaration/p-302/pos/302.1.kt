// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 302 -> sentence 302
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 302 -> sentence 302
 * NUMBER: 1
 * DESCRIPTION: precise types for nested class declared in an interface is static and needs no outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    class Nested
}

fun case_1() {
    val nested = I.Nested()
    nested checkType { check<I.Nested>() }
}
