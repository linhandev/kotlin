// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, nullability-lozenge -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Nullable types cannot be assigned to non-nullable types without smart cast
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(x: T?) {
    val y: Int = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 2
fun case_2(x: Int?) {
    val y: Int = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun <T : Any> case_3(x: T?) {
    val y: T = <!TYPE_MISMATCH!>x<!>
}
