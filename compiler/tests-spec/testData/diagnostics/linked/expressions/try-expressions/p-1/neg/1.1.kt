// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: try block without catch or finally clause is a syntax error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x = try { 1 }<!SYNTAX!><!>
    return x.toString()
}
