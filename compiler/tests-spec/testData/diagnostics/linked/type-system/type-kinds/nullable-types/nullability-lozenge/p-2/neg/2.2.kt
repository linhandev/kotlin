// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, nullability-lozenge -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Nullability lozenge does not allow nullable to non-null assignment across type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T, R> case_1(x: T?) {
    val y: R = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 2
fun case_2(x: Number?) {
    val y: Int = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 3
fun <T> case_3(x: T?) {
    val y: Any = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 4
fun case_4(x: List<String>?) {
    val y: List<String> = <!TYPE_MISMATCH!>x<!>
}
