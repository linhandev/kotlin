// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type invalid simpleIdentifier return in value parameter type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p51.neg1

fun case1(x: <!SYNTAX!>return<!>) {}
