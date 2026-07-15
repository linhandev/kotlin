// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Integer literal types are subtypes of built-in integer types in their allowed set
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(42)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: Number = 100
    checkSubtype<Number>(x)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Long = 1
    checkSubtype<Long>(x)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val arr = intArrayOf(1, 2, 3)
    checkSubtype<IntArray>(arr)
}


// TESTCASE NUMBER: 5
fun case_5(value: Int) {
    checkSubtype<Int>(value)
    checkSubtype<Number>(value)
}
