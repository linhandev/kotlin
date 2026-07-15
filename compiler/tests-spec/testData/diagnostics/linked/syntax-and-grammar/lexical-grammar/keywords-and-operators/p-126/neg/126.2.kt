// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 2
 * DESCRIPTION: Space inside REIFIED token as rei fied breaks reified modifier lexeme
 */

// TESTCASE NUMBER: 1
<!NOTHING_TO_INLINE!>inline<!> fun <rei <!SYNTAX!>fied<!> T<!SYNTAX!><!SYNTAX!><!>><!> <!SYNTAX!>brokenReified126<!><!SYNTAX!>(<!><!SYNTAX!>)<!><!SYNTAX!>:<!> <!SYNTAX!>String<!> <!SYNTAX!>=<!> <!SYNTAX!>"<!><!SYNTAX!>OK<!><!SYNTAX!>"<!>

fun case1(): String = "OK"
