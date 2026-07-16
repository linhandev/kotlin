// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * NUMBER: 1
 * DESCRIPTION: annotationUseSiteTarget invalid use site target
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p172.neg1

fun case1() { @<!SYNTAX!>invalid<!>:Suppress("U") fun f() {} }
