// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 137 -> sentence 137
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * NUMBER: 2
 * DESCRIPTION: jumpExpression missing expression after throw
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p137.neg2

fun case1() { throw<!SYNTAX!><!> }
