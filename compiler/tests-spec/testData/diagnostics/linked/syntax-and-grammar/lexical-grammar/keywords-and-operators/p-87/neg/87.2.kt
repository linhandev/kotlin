// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 2
 * DESCRIPTION: Space inside FINALLY token as fin ally breaks finally clause lexeme
 */

// TESTCASE NUMBER: 1
fun brokenFinally87(): String {
    try {
        return "OK"
    }<!SYNTAX!><!> <!UNREACHABLE_CODE!><!UNRESOLVED_REFERENCE!>fin<!> <!UNRESOLVED_REFERENCE!>ally<!> {
        Unit
    }<!>
}

fun case1(): String = "OK"
