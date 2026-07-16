// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 62 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: parenthesizedType invalid empty type inside parentheses
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p62.neg1

val value: (<!SYNTAX!><!>) = 1
