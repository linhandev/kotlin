// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * syntax-and-grammar, syntax-grammar -> paragraph 141 -> sentence 141
 * NUMBER: 1
 * DESCRIPTION: comparison trailing less than operator missing right operand
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p82.neg1

fun case1() { val y = 2 <!OVERLOAD_RESOLUTION_AMBIGUITY!><<!><!SYNTAX!><!> }
