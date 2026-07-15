// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 2
 * DESCRIPTION: Space inside GET token as ge t() breaks property getter lexeme
 */

// TESTCASE NUMBER: 1
<!MUST_BE_INITIALIZED!>val brokenGet59: Int<!>
    <!SYNTAX!>ge<!> <!SYNTAX!>t<!><!SYNTAX!>(<!><!SYNTAX!>)<!> <!SYNTAX!>=<!> <!SYNTAX!>1<!>

fun case1(): String {
    return "OK"
}
