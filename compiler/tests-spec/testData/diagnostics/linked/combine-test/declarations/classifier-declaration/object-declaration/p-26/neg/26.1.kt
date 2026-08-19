// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 26 -> sentence 26
 *                inheritance, overriding -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: object declaration cannot be open
 */

// TESTCASE NUMBER: 1
interface I

<!WRONG_MODIFIER_TARGET!>open<!> object O : I
