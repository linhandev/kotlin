// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 157 -> sentence 157
 * NUMBER: 1
 * DESCRIPTION: classModifier duplicate data modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p157.neg1

data <!REPEATED_MODIFIER!>data<!> class Case1(val x: Int)
