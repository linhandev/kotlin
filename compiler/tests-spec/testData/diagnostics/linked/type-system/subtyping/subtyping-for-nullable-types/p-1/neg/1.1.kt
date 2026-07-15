// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Foo rejects assigning B or B? to non-null A when only B <: A? holds
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
fun <T> mk(): T = TODO()
fun case_1() {
    val b: String? = mk<String?>()
    val ab: String = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val bQ: Int? = mk<Int?>()
    val abQ: Number = <!TYPE_MISMATCH!>bQ<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val s: String? = "a"
    val x: String = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val a: Any? = null
    val x: Any = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val i: Int? = 1
    val x: Int = <!TYPE_MISMATCH!>i<!>
}
