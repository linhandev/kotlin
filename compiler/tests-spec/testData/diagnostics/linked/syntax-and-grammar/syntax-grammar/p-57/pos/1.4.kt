// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 57 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: typeProjection with varianceModifier in
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p57.pos4

interface Sink<in T> { fun put(value: T) }
class NumberSink : Sink<Number> { override fun put(value: Number) {} }
fun case1(): Sink<<!REDUNDANT_PROJECTION!>in<!> Number> = NumberSink()
