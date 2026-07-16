// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 142 -> sentence 142
 * NUMBER: 1
 * DESCRIPTION: inOperator in keyword in expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p142.pos1

fun case1() { val b = 2 in 1..3 }
