// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_CHANGED_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 148 -> sentence 148
 * NUMBER: 1
 * DESCRIPTION: postfixUnaryOperator increment token
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p148.pos1

fun case1() { var i = 0; i++ }
