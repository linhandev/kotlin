// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 166 -> sentence 166
 * NUMBER: 1
 * DESCRIPTION: parameterModifier crossinline parameter modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p166.pos1

inline fun case1(crossinline f: () -> Unit) { f() }
