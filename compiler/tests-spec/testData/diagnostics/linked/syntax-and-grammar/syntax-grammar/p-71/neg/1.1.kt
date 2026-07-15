// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 71 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: loopStatement invalid for while combination
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p71.neg1

fun case1() { <!UNRESOLVED_REFERENCE!>forwhile<!> (true) { } }
