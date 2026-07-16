// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 2
 * DESCRIPTION: Space inside SET token as se t() breaks property setter lexeme
 */

// TESTCASE NUMBER: 1
var brokenSet60: Int = 0
    <!SYNTAX!>se<!> <!SYNTAX!>t<!><!SYNTAX!>(<!>value<!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{
        <!UNRESOLVED_REFERENCE!>field<!> = <!UNRESOLVED_REFERENCE!>value<!>
    }<!>

fun case1(): String {
    return "OK"
}
