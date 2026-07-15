// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 58 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeProjectionModifiers in varianceModifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p58.pos2

interface Cell<T>
fun case1(value: Cell<in Number>) {}
