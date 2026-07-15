// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: CONJ token with non-boolean operand causes type mismatch error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x = 5
    val result = <!TYPE_MISMATCH!>x<!> && true
    return "OK"
}
