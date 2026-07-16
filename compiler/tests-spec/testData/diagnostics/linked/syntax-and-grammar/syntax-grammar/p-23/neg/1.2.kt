// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 23 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeParameters numeric typeParameter
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p23.neg2

fun <<!SYNTAX!>1<!>, T> case1(value: T): T = value
