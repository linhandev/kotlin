// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 156 -> sentence 156
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * NUMBER: 1
 * DESCRIPTION: typeModifier invalid suspend type modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p156.neg1

val case1: <!WRONG_MODIFIER_TARGET!>suspend<!> Int = 1
