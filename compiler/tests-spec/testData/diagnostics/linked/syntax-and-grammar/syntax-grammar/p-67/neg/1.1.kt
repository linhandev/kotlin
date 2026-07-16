// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 67 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: statement invalid incomplete if statement
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p67.neg1

fun case1() { if<!SYNTAX!><!> <!SYNTAX!><!>}
