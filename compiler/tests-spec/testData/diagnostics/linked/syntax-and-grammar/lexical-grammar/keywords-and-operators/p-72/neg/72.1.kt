// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 1
 * DESCRIPTION: Standalone VAR token as statement causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    var<!SYNTAX!><!>
    return "OK"
}
