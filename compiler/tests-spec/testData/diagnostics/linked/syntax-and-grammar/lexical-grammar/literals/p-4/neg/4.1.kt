// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: DecDigits 999_ trailing separator instead of ending DecDigit
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = <!ILLEGAL_UNDERSCORE!>999_<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
