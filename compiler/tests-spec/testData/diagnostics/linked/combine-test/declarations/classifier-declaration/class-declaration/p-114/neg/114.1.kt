// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 114 -> sentence 114
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 114 -> sentence 114
 *                declarations, classifier-declaration, object-declaration -> paragraph 114 -> sentence 114
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 114 -> sentence 114
 * NUMBER: 1
 * DESCRIPTION: neither nested object inside a class nor top-level object may declare secondary constructors
 */

// TESTCASE NUMBER: 1
class Host {
    object Nested {
        <!CONSTRUCTOR_IN_OBJECT!>constructor()<!> {}
    }
}

// TESTCASE NUMBER: 2
object TopLevel {
    <!CONSTRUCTOR_IN_OBJECT!>constructor(tag: String)<!> {}
}
