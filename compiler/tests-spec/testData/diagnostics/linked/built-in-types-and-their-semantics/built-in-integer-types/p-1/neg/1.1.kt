// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-integer-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: built-in integer types are not subtypes of each other
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val s: Short = 1
    val i: Int = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val b: Byte = 1
    val s: Short = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val i: Int = 1
    val l: Long = <!TYPE_MISMATCH!>i<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val l: Long = 1L
    val i: Int = <!TYPE_MISMATCH!>l<!>
}


// TESTCASE NUMBER: 5
fun case_5(x: Int, y: Short, z: Byte, w: Long) {
    val a: Int = <!TYPE_MISMATCH!>y<!>
    val b: Short = <!TYPE_MISMATCH!>z<!>
    val c: Byte = <!TYPE_MISMATCH!>x<!>
    val d: Long = <!TYPE_MISMATCH!>x<!>
    checkSubtype<Int>(<!TYPE_MISMATCH!>w<!>)
}
