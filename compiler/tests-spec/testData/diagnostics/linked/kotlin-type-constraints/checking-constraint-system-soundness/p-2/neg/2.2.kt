// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, checking-constraint-system-soundness -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: reduction reports inference error when resolved S is not subtype of parameterized T
 */

fun <T> first1321(list: List<T>): T? = null

// TESTCASE NUMBER: 1
fun case_1() {
    first1321(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
}
