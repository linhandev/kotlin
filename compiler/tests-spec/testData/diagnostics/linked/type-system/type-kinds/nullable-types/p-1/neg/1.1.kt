// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Non-nullable types cannot hold null values
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int = <!NULL_FOR_NONNULL_TYPE!>null<!>
}

// TESTCASE NUMBER: 2
fun case_2(x: String?) {
    val y: String = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val x: List<Int> = <!NULL_FOR_NONNULL_TYPE!>null<!>
}
