// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 3
 * DESCRIPTION: Space inside TYPEOF token as ty peof breaks identifier lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!UNRESOLVED_REFERENCE!>ty<!> <!DEBUG_INFO_MISSING_UNRESOLVED, UNREACHABLE_CODE!>peof<!>
    return "OK"
}
