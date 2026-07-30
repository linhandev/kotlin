// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 17 -> sentence 17
 *                inheritance, overriding -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: data class cannot be declared open
 */

// TESTCASE NUMBER: 1
<!INCOMPATIBLE_MODIFIERS!>open<!> <!INCOMPATIBLE_MODIFIERS!>data<!> class Bad(val x: Int)
