// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Subtype relation does not hold across incompatible class hierarchies
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
interface IA
interface IB
class CA : IA
class CB : IB
fun case_1() {
    val a: CA = CA()
    val b: CB = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: Float = 1.0f
    val n: Int = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val l: Long = 1L
    val s: Short = <!TYPE_MISMATCH!>l<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val i: IA = CA()
    val b: IB = <!TYPE_MISMATCH!>i<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val n: Number = 1
    val s: String = <!TYPE_MISMATCH!>n<!>
}
