// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 132 -> sentence 132
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 142 -> sentence 142
 * syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: rangeTest when in range condition
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p132.pos1

fun case1() { when (2) { in 1..3 -> Unit; else -> Unit } }
