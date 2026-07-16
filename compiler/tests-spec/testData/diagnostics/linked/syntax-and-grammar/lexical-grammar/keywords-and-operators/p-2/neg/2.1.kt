// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: DOT token without preceding receiver expression causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!SYNTAX!>.<!><!UNRESOLVED_REFERENCE!>toString<!>()
    return "OK"
}
