// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 158 -> sentence 158
 * NUMBER: 2
 * DESCRIPTION: memberModifier lateinit property modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p158.pos2

class Case1 {
    lateinit var x: String
}
