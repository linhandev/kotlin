// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 150 -> sentence 150
 * NUMBER: 1
 * DESCRIPTION: navigationSuffix trailing dot
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p102.neg1

fun case1() { val x = "".<!SYNTAX!><!> }
