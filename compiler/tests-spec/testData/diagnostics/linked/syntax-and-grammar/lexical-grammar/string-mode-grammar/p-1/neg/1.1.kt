// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Unclosed QUOTE_OPEN line string at end of file
 */

// TESTCASE NUMBER: 1
fun case1(): String = "unclosed<!SYNTAX!><!>
