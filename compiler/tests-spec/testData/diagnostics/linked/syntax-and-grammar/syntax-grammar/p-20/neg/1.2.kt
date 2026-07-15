// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 20 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: constructorInvocation hard keyword return used as userType
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p20.neg3

class Case1 : <!SYNTAX!>return<!>(1)
