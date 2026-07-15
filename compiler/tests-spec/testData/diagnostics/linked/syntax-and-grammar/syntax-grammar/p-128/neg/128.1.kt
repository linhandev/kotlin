// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 128 -> sentence 128
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 35 -> sentence 35
 * syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: whenSubject missing subject expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p128.neg1

fun case1() { when (<!SYNTAX!><!>) { else -> Unit } }
