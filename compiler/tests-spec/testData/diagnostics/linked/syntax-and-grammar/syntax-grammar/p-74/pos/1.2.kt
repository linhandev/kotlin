// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 74 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: doWhileStatement NL between do and while
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p74.pos2

fun case1() { do { break }
while (<!UNREACHABLE_CODE!>true<!>); val loopDone = 1 }
