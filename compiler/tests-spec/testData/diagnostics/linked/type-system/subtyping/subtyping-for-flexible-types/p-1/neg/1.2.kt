// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-flexible-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Rigid types are not subtypes of unrelated flexible bounds
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val i: Int = 1
    val s: String = <!TYPE_MISMATCH!>i<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val b: Boolean = true
    val i: Int = <!TYPE_MISMATCH!>b<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val d: Double = 1.0
    val f: Float = <!TYPE_MISMATCH!>d<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val a: Any = "x"
    val i: Int = <!TYPE_MISMATCH!>a<!>
}

// TESTCASE NUMBER: 5
fun case_5() {
    val l: Long = 1L
    val i: Int = <!TYPE_MISMATCH!>l<!>
}
