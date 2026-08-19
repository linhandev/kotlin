// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: interface var cannot have a property initializer (no backing field)
 */

// TESTCASE NUMBER: 1
interface Bad {
    var x: Int = <!PROPERTY_INITIALIZER_IN_INTERFACE!>1<!>
}
