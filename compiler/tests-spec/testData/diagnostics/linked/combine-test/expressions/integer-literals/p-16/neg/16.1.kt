// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 16 -> sentence 16
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: lowercase l long suffix is illegal even beside valid Long separator literal
 */

// TESTCASE NUMBER: 1
fun case1(): Long {
    val valid: Long = 1_000L
    return 1<!WRONG_LONG_SUFFIX!>l<!> + valid
}
