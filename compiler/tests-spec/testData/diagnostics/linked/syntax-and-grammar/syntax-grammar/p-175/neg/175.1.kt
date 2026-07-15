// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 175 -> sentence 175
 * NUMBER: 1
 * DESCRIPTION: identifier missing simpleIdentifier after trailing dot
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p175.neg1

val case1: kotlin.<!SYNTAX!><!>
