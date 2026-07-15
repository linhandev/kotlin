// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 10 -> sentence 10
 * NUMBER: 3
 * DESCRIPTION: MultilineStringQuote unclosed in return block
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    return """""""<!UNRESOLVED_REFERENCE, UNSUPPORTED!>quote<!><!SYNTAX!><!>
}
