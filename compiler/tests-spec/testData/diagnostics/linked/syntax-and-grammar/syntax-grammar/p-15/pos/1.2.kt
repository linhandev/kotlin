// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 15 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: classBody with function member
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p15.pos2

class Case1 {
    fun value(): Int = 2
}
