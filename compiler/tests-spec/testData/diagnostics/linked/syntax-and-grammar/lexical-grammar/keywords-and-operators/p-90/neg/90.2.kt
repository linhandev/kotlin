// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 2
 * DESCRIPTION: Space inside WHILE token as wh ile breaks while loop lexeme
 */

// TESTCASE NUMBER: 1
fun brokenWhile90(): String {
    <!UNRESOLVED_REFERENCE!>wh<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>ile<!> (true)<!SYNTAX!><!> return "OK"
    <!UNREACHABLE_CODE!>return "NOK"<!>
}

fun case1(): String = "OK"
