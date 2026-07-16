// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 2
 * DESCRIPTION: Space inside PACKAGE token as pack age breaks package declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>pack<!> <!SYNTAX!>age<!> <!SYNTAX!>test<!><!SYNTAX!>.<!><!SYNTAX!>broken<!><!SYNTAX!>.<!><!SYNTAX!>p65<!>

fun case1(): String {
    return "OK"
}
