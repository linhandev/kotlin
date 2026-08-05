// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 13 -> sentence 13
 *                type-system, built-in-integer-types -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: trailing underscore in integer literal is illegal even beside valid separator literal
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val valid = 1_000
    return <!ILLEGAL_UNDERSCORE!>1_<!> + valid
}
