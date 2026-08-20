// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 81 -> sentence 81
 *                declarations, property-declaration -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: class body cannot redeclare primary constructor property
 */

// TESTCASE NUMBER: 1
class Bad(val <!REDECLARATION!>x<!>: Int) { val <!REDECLARATION!>x<!> = 0 }

