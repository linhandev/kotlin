// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 131 -> sentence 131
 * syntax-and-grammar, syntax-grammar -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: whenEntry missing arrow in entry
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p130.neg1

fun case1() { when (1) { 1 <!UNRESOLVED_REFERENCE!>Unit<!><!SYNTAX!><!> } }
