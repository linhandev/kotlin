// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 163 -> sentence 163
 * NUMBER: 1
 * DESCRIPTION: functionModifier tailrec function modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p163.pos1

tailrec fun case1(n: Int): Int = if (n <= 0) 0 else case1(n - 1)
