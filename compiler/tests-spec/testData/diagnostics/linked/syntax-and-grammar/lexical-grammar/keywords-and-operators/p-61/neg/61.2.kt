// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 2
 * DESCRIPTION: Incomplete receiver annotation @receiver without colon causes parser error
 */

// TESTCASE NUMBER: 1
fun <!SYNTAX!>@<!><!UNRESOLVED_REFERENCE!>receiver<!> <!SYNTAX!>Suppress<!>(<!SYNTAX!>"<!><!SYNTAX!><!SYNTAX!><!>WARNING<!><!SYNTAX!>"<!><!SYNTAX!>)<!> <!SYNTAX!>String<!><!SYNTAX!>.<!><!SYNTAX!>broken<!><!SYNTAX!>(<!><!SYNTAX!>)<!><!SYNTAX!>:<!> <!SYNTAX!>String<!> <!SYNTAX!>=<!> <!SYNTAX!>this<!>

fun case1(): String {
    return "OK"
}
