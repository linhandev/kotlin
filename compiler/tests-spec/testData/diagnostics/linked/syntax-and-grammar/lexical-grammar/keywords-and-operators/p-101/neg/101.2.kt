// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 2
 * DESCRIPTION: Space inside DYNAMIC token as dyn amic breaks identifier lexeme
 */

// TESTCASE NUMBER: 1
fun brokenDynamic101(): String {
    return <!UNRESOLVED_REFERENCE!>dyn<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>amic<!>(<!SYNTAX!><!>)
}

fun case1(): String = "OK"
