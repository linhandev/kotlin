// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 15 -> sentence 15
 *                type-system, built-in-integer-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: trailing consecutive underscores in integer literal (1__) is illegal even beside valid separator literal
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val valid = 1_000
    return <!ILLEGAL_UNDERSCORE!>1__<!> + valid
}
