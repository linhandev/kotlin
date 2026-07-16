// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 146 -> sentence 146
 * NUMBER: 1
 * DESCRIPTION: asOperator dangling as without type
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p146.neg1

typealias <!SYNTAX!>1<!> = Int
