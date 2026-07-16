// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kcallable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-callable values cannot be assigned to kotlin.reflect.KCallable
 * HELPERS: checkType
 */

import kotlin.reflect.KCallable

// TESTCASE NUMBER: 1
fun case_1() {
    val c: KCallable<*> = <!TYPE_MISMATCH!>"not callable"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val c: KCallable<*> = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}
