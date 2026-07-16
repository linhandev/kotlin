// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: DecDigits 007 with leading zero in multi-digit integer literal
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = 0<!SYNTAX!><!UNSUPPORTED!>0<!>7<!>
}
