// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 37 -> sentence 37
 * NUMBER: 2
 * DESCRIPTION: Space after @ in file annotation @ file: breaks file annotation syntax
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>@<!> <!SYNTAX!>file<!><!SYNTAX!>:<!><!SYNTAX!>Suppress<!><!SYNTAX!>(<!><!SYNTAX!>"<!><!SYNTAX!>WARNING<!><!SYNTAX!>"<!><!SYNTAX!>)<!>
fun case1(): String {
    return "OK"
}
