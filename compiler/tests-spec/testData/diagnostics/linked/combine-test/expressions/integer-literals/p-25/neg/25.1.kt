// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 25 -> sentence 25
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: separator integer literal beyond Long.MAX_VALUE is out of range even beside valid separator Long literals
 */

// TESTCASE NUMBER: 1
fun case1(): Long {
    val valid: Long = 1_000L
    val maxLong: Long = 9_223_372_036_854_775_807
    val x = <!INT_LITERAL_OUT_OF_RANGE!>9_223_372_036_854_775_808<!>
    return valid
}
