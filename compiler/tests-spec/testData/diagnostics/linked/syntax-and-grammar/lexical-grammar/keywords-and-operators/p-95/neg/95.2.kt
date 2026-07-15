// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 2
 * DESCRIPTION: Space inside AS token as a s breaks cast lexeme
 */

// TESTCASE NUMBER: 1
fun brokenAs95(): String {
    return "OK" <!UNRESOLVED_REFERENCE!>a<!> <!UNRESOLVED_REFERENCE!>s<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>String<!><!SYNTAX!><!>
}

fun case1(): String = "OK"
