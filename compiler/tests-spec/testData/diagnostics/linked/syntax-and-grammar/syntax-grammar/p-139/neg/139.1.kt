// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 139 -> sentence 139
 * NUMBER: 1
 * DESCRIPTION: assignmentAndOperator missing rhs after plus assign
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p139.neg1

fun case1() { var x = 0; x <!DEBUG_INFO_MISSING_UNRESOLVED!>+=<!><!SYNTAX!><!> }
