// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * NUMBER: 1
 * DESCRIPTION: callSuffix missing closing paren
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p103.neg1

fun f(): Int = 1

fun case1() { val x = f( <!SYNTAX!><!>}
