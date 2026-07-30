// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 300 -> sentence 300
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 300 -> sentence 300
 * NUMBER: 1
 * DESCRIPTION: precise types for nested class can access outer companion object members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        const val K = 10
    }

    class Nested {
        fun k(): Int = K
    }
}

fun case_1() {
    Outer.Nested().k() checkType { check<Int>() }
    checkSubtype<Int>(Outer.Nested().k())
}
