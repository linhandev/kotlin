// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 150 -> sentence 150
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 151 -> sentence 151
 * NUMBER: 2
 * DESCRIPTION: memberAccessOperator safe call navigation
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p150.pos2

class Case1 { companion object { val X = 1 } }
