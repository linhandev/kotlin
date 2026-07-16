// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 158 -> sentence 158
 * NUMBER: 1
 * DESCRIPTION: memberModifier override without super member
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p158.neg1

class Case1 { <!NOTHING_TO_OVERRIDE!>override<!> fun f() {} }
