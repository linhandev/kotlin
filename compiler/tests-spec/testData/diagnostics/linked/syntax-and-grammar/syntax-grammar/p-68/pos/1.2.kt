// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 68 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: label AT_POST_WS before simpleIdentifier
 * UNEXPECTED BEHAVIOUR
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p68.pos2

fun labeled() { outer@ while (true) { break@outer } }
