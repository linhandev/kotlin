// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 160 -> sentence 160
 * NUMBER: 1
 * DESCRIPTION: varianceModifier duplicate out variance
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p160.neg1

class Case1<out <!REPEATED_MODIFIER!>out<!> T>(val v: T)
