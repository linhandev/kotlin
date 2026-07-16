// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 152 -> sentence 152
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 154 -> sentence 154
 * syntax-and-grammar, syntax-grammar -> paragraph 159 -> sentence 159
 * NUMBER: 3
 * DESCRIPTION: modifiers annotation and visibility modifier combination on class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p152.pos3

@Deprecated("x") internal class Case3
