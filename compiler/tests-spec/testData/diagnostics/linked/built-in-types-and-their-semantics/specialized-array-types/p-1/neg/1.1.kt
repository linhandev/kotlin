// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, specialized-array-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: built-in-types-and-their-semantics, built-in-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: specialized array types are distinct from kotlin.Array of the corresponding element type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: IntArray = intArrayOf(1)
    val b: Array<Int> = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a: Array<Int> = arrayOf(1)
    val b: IntArray = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a: DoubleArray = doubleArrayOf(1.0)
    val b: Array<Double> = <!TYPE_MISMATCH!>a<!>
}
