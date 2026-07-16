// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 59 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeProjectionModifier varianceModifier in
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p59.pos2

interface Sink<T>
fun accept(s: Sink<in CharSequence>) {}
