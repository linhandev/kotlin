// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 145 -> sentence 145
 * NUMBER: 1
 * DESCRIPTION: multiplicativeOperator dangling star inside parenthesized expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p145.neg1

fun case1() { val x = (1 <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!>) }
