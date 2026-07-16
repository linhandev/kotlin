// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 140 -> sentence 140
 * NUMBER: 1
 * DESCRIPTION: equalityOperator double equals in expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p140.pos1

fun case1() { val b = 1 == 2 }
