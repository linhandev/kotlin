// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 89 -> sentence 89
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 90 -> sentence 90
 * syntax-and-grammar, syntax-grammar -> paragraph 144 -> sentence 144
 * NUMBER: 1
 * DESCRIPTION: additiveExpression trailing plus operator missing right operand
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p89.neg1

fun case1() { val y = (2 <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!>) }
