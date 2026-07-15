// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: DECR token used with val (immutable) variable causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x = 5
    <!VAL_REASSIGNMENT!>x<!>--
    return "OK"
}
