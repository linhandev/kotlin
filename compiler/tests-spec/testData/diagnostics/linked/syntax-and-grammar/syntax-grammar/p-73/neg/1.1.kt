// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 73 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: whileStatement missing opening parenthesis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p73.neg1

fun case1() { while<!SYNTAX!><!> <!FUNCTION_EXPECTED!>true<!> { <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>break<!> } }
