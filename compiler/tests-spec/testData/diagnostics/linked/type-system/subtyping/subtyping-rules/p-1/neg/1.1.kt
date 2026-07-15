// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-rules -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Int and other types are not subtypes of Nothing
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val n: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val n: Nothing = <!TYPE_MISMATCH!>"x"<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val n: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val a: Any = 1
    val n: Nothing = <!TYPE_MISMATCH!>a<!>
}

// TESTCASE NUMBER: 5
fun case_5() {
    val i: Int = 1
    val n: Nothing = <!TYPE_MISMATCH!>i<!>
}
