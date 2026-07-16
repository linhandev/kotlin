// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 154 -> sentence 154
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 157 -> sentence 157
 * syntax-and-grammar, syntax-grammar -> paragraph 165 -> sentence 165
 * NUMBER: 2
 * DESCRIPTION: modifier class and inheritance modifiers on class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p154.pos2

open class Case2

data class Case3(val x: Int)
