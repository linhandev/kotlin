// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 2
 * DESCRIPTION: Space inside IF token as i f breaks if expression lexeme
 */

// TESTCASE NUMBER: 1
fun brokenIf82(flag: Boolean): String = <!UNRESOLVED_REFERENCE!>i<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>f<!> (flag) <!SYNTAX!>"<!><!SYNTAX!>OK<!><!SYNTAX!>"<!> <!SYNTAX!>else<!> <!SYNTAX!>"<!><!SYNTAX!>NOK<!><!SYNTAX!>"<!>

fun case1(): String {
    return "OK"
}
