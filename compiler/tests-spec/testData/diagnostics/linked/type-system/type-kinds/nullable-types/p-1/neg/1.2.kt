// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Nullable to non-null misuse produces type mismatch errors
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?) {
    checkSubtype<String>(<!TYPE_MISMATCH!>x<!>)
}

// TESTCASE NUMBER: 2
fun case_2(x: Int?) {
    val y: Int = <!TYPE_MISMATCH!>x<!>
}
