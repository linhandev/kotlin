// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 97 -> sentence 97
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 96 -> sentence 96
 * NUMBER: 1
 * DESCRIPTION: parenthesizedDirectlyAssignableExpression assign to literal paren
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p97.neg1

fun case1() { (<!VARIABLE_EXPECTED!>1<!>) = 2 }
