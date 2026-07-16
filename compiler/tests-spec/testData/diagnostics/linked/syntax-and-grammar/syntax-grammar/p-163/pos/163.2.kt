// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 163 -> sentence 163
 * NUMBER: 2
 * DESCRIPTION: functionModifier operator infix inline external and suspend modifiers
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p163.pos2

inline fun inlineCase() {}

suspend fun suspendCase(): Int = 1

external fun externalCase(): Int

infix fun Int.myAdd(x: Int): Int = this + x

class Num(val v: Int) {
    operator fun plus(other: Num): Num = Num(v + other.v)
}
