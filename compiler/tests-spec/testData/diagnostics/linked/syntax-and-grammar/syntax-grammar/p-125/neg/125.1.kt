// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 125 -> sentence 125
 * NUMBER: 1
 * DESCRIPTION: thisExpression this outside class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p125.neg1

fun case1() { <!NO_THIS!>this<!> }
