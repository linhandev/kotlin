// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Line comment with carriage return
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    // comment with CR:
<!UNRESOLVED_REFERENCE!>invalid<!>
    return "OK"
}
