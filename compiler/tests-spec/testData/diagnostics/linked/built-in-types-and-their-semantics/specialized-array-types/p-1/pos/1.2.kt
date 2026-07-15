// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, specialized-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: specialized array types support size constructor and indexed access
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a = IntArray(3)
    checkSubtype<IntArray>(a)
    a.size checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a = DoubleArray(2)
    a[0] = 1.0
    a[1] checkType { check<Double>() }
}


// TESTCASE NUMBER: 3
fun case_3(a: LongArray) {
    a.size checkType { check<Int>() }
}
