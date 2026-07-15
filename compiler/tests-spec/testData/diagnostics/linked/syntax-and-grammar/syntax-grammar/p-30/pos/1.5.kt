// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 30 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: companionObject with function member
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p30.pos5

class Case1 { companion object { fun create(): Case1 = Case1() } }
