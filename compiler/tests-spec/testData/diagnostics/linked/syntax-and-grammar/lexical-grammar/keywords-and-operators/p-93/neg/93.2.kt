// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 2
 * DESCRIPTION: Space inside CONTINUE token as con tinue breaks continue statement lexeme
 */

// TESTCASE NUMBER: 1
fun brokenContinue93(): String {
    <!UNRESOLVED_REFERENCE!>con<!> <!DEBUG_INFO_MISSING_UNRESOLVED, UNREACHABLE_CODE!>tinue<!>
    return "OK"
}

fun case1(): String = "OK"
