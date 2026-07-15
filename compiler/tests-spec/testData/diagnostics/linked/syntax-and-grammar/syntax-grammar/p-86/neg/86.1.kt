// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: elvis missing colon after question mark
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p86.neg1

fun case1() { val x = null <!SYNTAX!>? 1<!> }
