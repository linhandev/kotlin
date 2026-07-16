// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Decimal literal 2a with invalid non-DecDigit letter a
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = 2<!UNRESOLVED_REFERENCE, UNSUPPORTED!>a<!><!SYNTAX!><!>
}
