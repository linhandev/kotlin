// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 64 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: parenthesizedUserType invalid numeric userType inside
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p64.neg2

fun case1(x: (<!SYNTAX!>1<!>) & Any) {}
