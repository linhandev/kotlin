// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: duplicate type parameter name is illegal
 */

// TESTCASE NUMBER: 1
class Bad<<!REDECLARATION!>T<!>, <!REDECLARATION!>T<!>>
