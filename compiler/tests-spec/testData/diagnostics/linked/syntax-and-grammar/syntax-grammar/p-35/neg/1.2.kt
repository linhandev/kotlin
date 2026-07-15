// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 35 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: variableDeclaration numeric simpleIdentifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p35.neg2

val <!SYNTAX!>2<!>: Int = 2
