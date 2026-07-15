// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 2
 * DESCRIPTION: simpleIdentifier numeric token not allowed
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p174.neg2

val <!SYNTAX!>1<!>: Int = 1
