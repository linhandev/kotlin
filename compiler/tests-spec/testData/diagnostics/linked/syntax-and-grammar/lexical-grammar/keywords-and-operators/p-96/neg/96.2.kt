// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 96 -> sentence 96
 * NUMBER: 2
 * DESCRIPTION: Space inside IS token as i s breaks type check lexeme
 */

// TESTCASE NUMBER: 1
fun brokenIs96(): String {
    return "OK" <!UNRESOLVED_REFERENCE!>i<!> <!UNRESOLVED_REFERENCE!>s<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>String<!><!SYNTAX!><!>
}

fun case1(): String = "OK"
