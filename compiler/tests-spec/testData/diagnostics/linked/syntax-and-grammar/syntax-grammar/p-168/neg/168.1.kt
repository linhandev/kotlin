// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 168 -> sentence 168
 * NUMBER: 1
 * DESCRIPTION: platformModifier expect on local class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p168.neg1

fun case1() { <!WRONG_MODIFIER_TARGET!>expect<!> class L }
