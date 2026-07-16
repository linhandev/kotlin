// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 22 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: explicitDelegation property reference expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p22.pos5

interface Base { val value: Int }
object DefaultBase : Base { override val value: Int = 5 }
class Case1 : Base by DefaultBase
