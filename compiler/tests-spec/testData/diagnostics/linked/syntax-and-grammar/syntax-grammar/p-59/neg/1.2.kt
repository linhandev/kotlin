// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 59 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeProjectionModifier missing type after varianceModifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p59.neg2

interface Sink<in T> { fun put(value: T) }
val value: Sink<<!SYNTAX!><!>> = object : Sink<Int> { override fun put(value: Int) {} }
