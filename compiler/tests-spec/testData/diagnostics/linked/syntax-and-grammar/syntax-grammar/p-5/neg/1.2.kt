// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 5 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: fileAnnotation after packageHeader violates order
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p5.neg2

@file:Suppress("X")

<!SYNTAX!>extra<!>

val case1: Int = 1
