// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Bar rejects assigning nullable B? to non-null A
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
fun <T> mk(): T = TODO()
fun case_1() {
    val bQ: Int? = mk<Int?>()
    val abQ: Number = <!TYPE_MISMATCH!>bQ<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s: String? = null
    val x: CharSequence = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val n: Number? = 1
    val x: Int = <!TYPE_MISMATCH!>n<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val a: Any? = 1
    val x: String = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val l: List<Int>? = null
    val x: List<Int> = <!TYPE_MISMATCH!>l<!>
}
