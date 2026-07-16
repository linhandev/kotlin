// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 2
 * DESCRIPTION: Space inside IMPORT token as im port breaks import directive lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>im<!> <!SYNTAX!>port<!> <!SYNTAX!>kotlin<!><!SYNTAX!>.<!><!SYNTAX!>collections<!><!SYNTAX!>.<!><!SYNTAX!>emptyList<!>

fun case1(): String {
    return "OK"
}
