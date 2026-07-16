// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 152 -> sentence 152
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 154 -> sentence 154
 * NUMBER: 1
 * DESCRIPTION: modifiers invalid modifier token
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p152.neg1

fun case1() { <!UNRESOLVED_REFERENCE!>invalid<!><!SYNTAX!><!> fun f() {} }
