// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 31 -> sentence 31
 * NUMBER: 2
 * DESCRIPTION: RANGE token without end operand in for-loop 1.. causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    for (i in 1..<!SYNTAX!><!>) {
    }
    return "OK"
}
