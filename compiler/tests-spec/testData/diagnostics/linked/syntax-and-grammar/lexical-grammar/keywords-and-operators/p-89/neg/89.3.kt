// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 3
 * DESCRIPTION: Incomplete do-while DO without while causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenDoWhile89(): String {
    do {
        return "OK"
    }<!SYNTAX!><!>
}

fun case1(): String = "OK"
