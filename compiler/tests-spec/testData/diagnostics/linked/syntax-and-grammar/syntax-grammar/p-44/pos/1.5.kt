// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 44 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: parameter generic type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p44.pos5

fun case1(values: List<Int>): Int = values.size
