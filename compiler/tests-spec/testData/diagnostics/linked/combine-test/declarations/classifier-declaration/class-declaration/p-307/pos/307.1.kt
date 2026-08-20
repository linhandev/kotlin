// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 307 -> sentence 307
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 307 -> sentence 307
 * NUMBER: 1
 * DESCRIPTION: precise types for nested class can declare its own independent type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested<U>(val u: U)
}

fun case_1() {
    Outer.Nested("a").u checkType { check<String>() }
    checkSubtype<String>(Outer.Nested("a").u)
}
