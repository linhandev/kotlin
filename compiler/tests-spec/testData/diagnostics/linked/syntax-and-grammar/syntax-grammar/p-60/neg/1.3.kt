// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 60 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: functionType invalid empty functionTypeParameters
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p60.neg3

val value: (<!SYNTAX!>,<!> <!SYNTAX!><!>) -> Int = { 1 }
