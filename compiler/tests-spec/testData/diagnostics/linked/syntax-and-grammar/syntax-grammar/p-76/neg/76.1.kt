// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 1
 * DESCRIPTION: semi leading semicolon before statement
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p76.neg1

fun case1() { ;<!SYNTAX!>,<!> val y = 2 }
