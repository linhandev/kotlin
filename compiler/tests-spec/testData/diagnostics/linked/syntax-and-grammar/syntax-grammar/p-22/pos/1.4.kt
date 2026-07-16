// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 22 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: explicitDelegation object expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p22.pos4

interface Base { fun value(): Int }
class Case1 : Base by object : Base { override fun value(): Int = 4 }
