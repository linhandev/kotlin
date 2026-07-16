// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: Space before @ in label definition loop @ breaks label syntax
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!UNRESOLVED_REFERENCE!>loop<!> <!SYNTAX!>@ for (i in 1..3)<!> <!UNUSED_LAMBDA_EXPRESSION!>{
    }<!>
    return "OK"
}
