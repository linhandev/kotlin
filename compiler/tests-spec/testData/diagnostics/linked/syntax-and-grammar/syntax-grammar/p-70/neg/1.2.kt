// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: block missing opening brace
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p70.neg2

<!NON_MEMBER_FUNCTION_NO_BODY!>fun case1()<!>  val x = 1 <!SYNTAX!>}<!>
