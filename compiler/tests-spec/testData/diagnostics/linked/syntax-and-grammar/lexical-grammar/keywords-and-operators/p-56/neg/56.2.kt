// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 2
 * DESCRIPTION: Incomplete file annotation @file without colon causes parser error
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>@<!><!SYNTAX!>file<!> <!SYNTAX!>Suppress<!><!SYNTAX!>(<!><!SYNTAX!>"<!><!SYNTAX!>WARNING<!><!SYNTAX!>"<!><!SYNTAX!>)<!>

fun case1(): String {
    return "OK"
}
