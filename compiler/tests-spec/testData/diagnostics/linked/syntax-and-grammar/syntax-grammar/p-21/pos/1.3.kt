// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 21 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: annotatedDelegationSpecifier multiple Suppress annotations on userType in explicitDelegation
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p21.pos3

interface Base { fun value(): Int }
class DefaultBase : Base { override fun value(): Int = 1 }
class Case1 : @Suppress("X") @Suppress("Y") Base by DefaultBase()
