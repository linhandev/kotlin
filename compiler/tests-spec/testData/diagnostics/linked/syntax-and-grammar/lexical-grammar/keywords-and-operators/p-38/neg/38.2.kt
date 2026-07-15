// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 38 -> sentence 38
 * NUMBER: 2
 * DESCRIPTION: AT glued to file annotation @ @file:Suppress breaks AT_BOTH_WS parsing
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>@<!> @file:Suppress("WARNING")
fun case1(): String {
    return "OK"
}
