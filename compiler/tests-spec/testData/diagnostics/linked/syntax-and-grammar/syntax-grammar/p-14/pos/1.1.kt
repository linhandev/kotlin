// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 14 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: primaryConstructor implicit constructor with classParameters
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p14.pos1

class Case1(val value: Int)
