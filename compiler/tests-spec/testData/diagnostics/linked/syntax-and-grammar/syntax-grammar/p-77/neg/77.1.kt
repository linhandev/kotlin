// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 77 -> sentence 77
 * NUMBER: 1
 * DESCRIPTION: semis missing separator between statements on same line
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p77.neg1

fun case1() { val a = 1<!SYNTAX!><!> val b = 2 }
