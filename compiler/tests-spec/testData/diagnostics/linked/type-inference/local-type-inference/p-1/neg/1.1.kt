// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: later statement usage cannot re-infer type of earlier local property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = 1
    checkSubtype<String>(<!TYPE_MISMATCH!>x<!>)
}
