// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Shebang line followed directly by code without newline
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>#<!><!SYNTAX!>!<!><!SYNTAX!>/<!><!SYNTAX!>usr<!><!SYNTAX!>/<!><!SYNTAX!>bin<!><!SYNTAX!>/<!><!SYNTAX!>env<!> <!SYNTAX!>kotlinfun<!> <!SYNTAX!>case1<!><!SYNTAX!>(<!><!SYNTAX!>)<!><!SYNTAX!>:<!> <!SYNTAX!>String<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{ return <!TYPE_MISMATCH!>"OK"<!> }<!>
