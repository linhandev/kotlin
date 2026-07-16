// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Unclosed QUOTE_OPEN line string with embedded newline
 */

// TESTCASE NUMBER: 1
fun case1(): String = "abc<!SYNTAX!><!>
<!SYNTAX!>def<!><!SYNTAX!>"<!><!SYNTAX!><!>
