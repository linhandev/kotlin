// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: single-line string cannot contain unescaped newline
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    return "line1<!SYNTAX!><!>
<!UNREACHABLE_CODE, UNRESOLVED_REFERENCE!>line2<!><!SYNTAX!>"<!>
}
