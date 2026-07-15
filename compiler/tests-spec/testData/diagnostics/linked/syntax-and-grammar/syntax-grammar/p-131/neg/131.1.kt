// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 131 -> sentence 131
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * syntax-and-grammar, syntax-grammar -> paragraph 132 -> sentence 132
 * syntax-and-grammar, syntax-grammar -> paragraph 133 -> sentence 133
 * NUMBER: 1
 * DESCRIPTION: whenCondition missing condition in entry
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p131.neg1

fun case1() { when (1) { <!SYNTAX!><!>-> Unit } }
