// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 2
 * DESCRIPTION: Space inside FOR token as fo r breaks for loop lexeme
 */

// TESTCASE NUMBER: 1
fun brokenFor88(): String {
    <!UNRESOLVED_REFERENCE!>fo<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>r<!> (<!UNRESOLVED_REFERENCE!>i<!> in 1..1)<!SYNTAX!><!> return "OK"
    <!UNREACHABLE_CODE!>return "NOK"<!>
}

fun case1(): String = "OK"
