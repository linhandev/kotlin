// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: Invalid LineComment in Hidden-only region
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val a = 1
    // broken hidden comment:
<!UNRESOLVED_REFERENCE!>broken<!>
    return "OK"
}
