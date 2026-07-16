// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 31 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: functionValueParameters invalid parameter name hard keyword if
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p31.neg3

fun case1(<!SYNTAX!>if<!>: Int): Int = 1
