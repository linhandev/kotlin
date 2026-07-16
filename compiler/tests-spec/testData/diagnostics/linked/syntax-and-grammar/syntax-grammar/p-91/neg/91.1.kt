// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 92 -> sentence 92
 * syntax-and-grammar, syntax-grammar -> paragraph 146 -> sentence 146
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: asExpression trailing as operator missing type
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p91.neg1

typealias <!SYNTAX!>2<!> = Int
