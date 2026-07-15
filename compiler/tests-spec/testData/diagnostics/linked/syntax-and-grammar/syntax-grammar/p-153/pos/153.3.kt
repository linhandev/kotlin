// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 153 -> sentence 153
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 166 -> sentence 166
 * NUMBER: 3
 * DESCRIPTION: parameterModifiers annotation and vararg combination
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p153.pos3

fun case1(@Suppress("UNUSED_PARAMETER") vararg xs: Int) {}
