// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 33 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: functionDeclaration invalid typeParameter name
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p33.neg1

fun <<!SYNTAX!>return<!>> case1(): Int = 1
