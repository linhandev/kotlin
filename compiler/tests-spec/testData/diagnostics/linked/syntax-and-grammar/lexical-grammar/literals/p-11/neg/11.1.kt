// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: HexLiteral 0x__1 with consecutive HexDigitOrSeparator underscores violates BNF
 */

// TESTCASE NUMBER: 1
fun case1() {
    val value = <!ILLEGAL_UNDERSCORE!>0x__1<!>
}
