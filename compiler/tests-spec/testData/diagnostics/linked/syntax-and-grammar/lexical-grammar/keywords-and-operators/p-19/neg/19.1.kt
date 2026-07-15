// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Standalone EXCL token as statement causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!UNREACHABLE_CODE, UNRESOLVED_REFERENCE!>!<!>
    return "OK"
}
