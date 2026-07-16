// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 2
 * DESCRIPTION: Space inside FUN token as fu n breaks function declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>fu<!> <!SYNTAX!>n<!> <!SYNTAX!>broken69<!><!SYNTAX!>(<!><!SYNTAX!>)<!><!SYNTAX!>:<!> <!SYNTAX!>String<!> <!SYNTAX!>=<!> <!SYNTAX!>"<!><!SYNTAX!>OK<!><!SYNTAX!>"<!>

fun case1(): String {
    return "OK"
}
