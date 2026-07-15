// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 44 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: parameter invalid type simpleIdentifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p44.neg3

fun case1(value: <!SYNTAX!>return<!>): Int = 1
