// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Incomplete RealLiteral 1. with missing fraction DecDigits
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    return 1.<!SYNTAX!><!>
}
