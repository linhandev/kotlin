// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: LineStrRef $when hard keyword without escaping
 */

// TESTCASE NUMBER: 1
fun case1(): String = "x=$<!SYNTAX!>when<!>"
