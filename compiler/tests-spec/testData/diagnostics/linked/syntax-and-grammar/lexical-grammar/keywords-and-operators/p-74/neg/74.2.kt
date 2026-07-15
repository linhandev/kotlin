// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 2
 * DESCRIPTION: Space inside CONSTRUCTOR token as constr uctor breaks constructor declaration lexeme
 */

// TESTCASE NUMBER: 1
class Broken74 {
    <!SYNTAX!>constr<!> <!SYNTAX!>uctor<!><!SYNTAX!>(<!><!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
}

fun case1(): String {
    return "OK"
}
