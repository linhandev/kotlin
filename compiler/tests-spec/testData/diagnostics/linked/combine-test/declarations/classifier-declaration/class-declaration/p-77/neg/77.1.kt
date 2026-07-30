// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 77 -> sentence 77
 * NUMBER: 1
 * DESCRIPTION: interface cannot declare primary constructor
 */

// TESTCASE NUMBER: 1
interface I<!CONSTRUCTOR_IN_INTERFACE!>(val x: Int)<!>

