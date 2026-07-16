// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 44 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: parameter numeric simpleIdentifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p44.neg2

fun case1(<!SYNTAX!>1<!>: Int): Int = 1
