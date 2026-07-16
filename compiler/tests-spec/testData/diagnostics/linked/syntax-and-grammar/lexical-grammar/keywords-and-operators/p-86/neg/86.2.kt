// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 2
 * DESCRIPTION: Space inside CATCH token as cat ch breaks catch clause lexeme
 */

// TESTCASE NUMBER: 1
fun brokenCatch86(): String {
    try {
        return "OK"
    }<!SYNTAX!><!> <!UNREACHABLE_CODE!><!UNRESOLVED_REFERENCE!>cat<!> <!UNRESOLVED_REFERENCE!>ch<!> (<!UNRESOLVED_REFERENCE!>e<!><!SYNTAX!>: Exception<!>) {
        <!RETURN_NOT_ALLOWED!>return<!> "NOK"
    }<!>
}

fun case1(): String = "OK"
