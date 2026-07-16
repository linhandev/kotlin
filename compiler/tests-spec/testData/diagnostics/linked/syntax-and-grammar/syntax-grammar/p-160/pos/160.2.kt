// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 160 -> sentence 160
 * NUMBER: 2
 * DESCRIPTION: varianceModifier in variance type parameter
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p160.pos2

interface Sink<in T> {
    fun put(value: T)
}
