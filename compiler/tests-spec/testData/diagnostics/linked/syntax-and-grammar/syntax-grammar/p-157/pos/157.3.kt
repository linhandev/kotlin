// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 157 -> sentence 157
 * NUMBER: 3
 * DESCRIPTION: classModifier inner and value class modifiers
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p157.pos3

class Outer {
    inner class Inner
}

@JvmInline
value class V(val x: Int)
