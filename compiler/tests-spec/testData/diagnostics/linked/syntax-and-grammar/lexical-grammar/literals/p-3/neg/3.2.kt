// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Leading underscore _1 is not a valid DecDigitOrSeparator sequence start
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = <!UNRESOLVED_REFERENCE!>_1<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
