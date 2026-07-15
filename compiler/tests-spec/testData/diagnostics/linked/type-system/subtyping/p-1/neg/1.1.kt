// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Unrelated types are not subtypes and cannot substitute for each other
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
open class Base1
class Derived1 : Base1()
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
    val s: String = "x"
    val i: Int = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val b: Base1 = Base1()
    val d: Derived1 = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val d: Double = 1.0
    val i: Int = <!TYPE_MISMATCH!>d<!>
}
