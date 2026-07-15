// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * NUMBER: 1
 * DESCRIPTION: annotationUseSiteTarget file annotation target
 */

// TESTCASE NUMBER: 1
@file:Suppress("UNUSED")

package syntax.grammar.p172.pos1

fun case1() {}
