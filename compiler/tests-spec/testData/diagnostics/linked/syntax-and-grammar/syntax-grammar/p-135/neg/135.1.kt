// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 135 -> sentence 135
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: catchBlock missing catch parameter type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p135.neg1

fun case1() { try { 1 } catch (e<!SYNTAX!><!>) { 0 } }
