// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-floating-point-arithmetic-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, subtyping, subtyping-for-flexible-types -> paragraph 1 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: built-in floating point types are not subtypes of each other or kotlin.Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: Float = 1.0f
    val d: Double = <!TYPE_MISMATCH!>f<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val d: Double = 1.0
    val f: Float = <!TYPE_MISMATCH!>d<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val i: Int = 1
    val f: Float = <!TYPE_MISMATCH!>i<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val s: String = "1.0"
    val d: Double = <!TYPE_MISMATCH!>s<!>
}
