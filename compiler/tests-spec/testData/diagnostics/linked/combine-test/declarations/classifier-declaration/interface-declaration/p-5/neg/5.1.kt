// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: interface val cannot have a property initializer (no backing field); default must use accessor body instead
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String = <!PROPERTY_INITIALIZER_IN_INTERFACE!>"anon"<!>
}
