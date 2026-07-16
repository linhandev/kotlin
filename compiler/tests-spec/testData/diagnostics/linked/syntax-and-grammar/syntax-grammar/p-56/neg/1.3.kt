// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 56 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: simpleUserType missing opening angle bracket in typeArguments
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p56.neg3

val value: <!UNRESOLVED_REFERENCE!>ListInt<!><!SYNTAX!>> = emptyList()<!>
