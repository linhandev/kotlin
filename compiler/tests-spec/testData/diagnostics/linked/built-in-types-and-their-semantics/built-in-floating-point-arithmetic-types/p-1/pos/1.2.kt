// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-floating-point-arithmetic-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: built-in floating point arithmetic preserves kotlin.Float and kotlin.Double types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Float = 1.0f + 2.0f
    val y: Float = x - 1.0f
    val z: Float = x * 2.0f
    checkSubtype<Float>(x + y + z)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: Double = 1.0 + 2.0
    val y: Double = x - 1.0
    val z: Double = x / 2.0
    checkSubtype<Double>(y + z)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val mixed: Double = 1.0f + 2.0
    checkSubtype<Double>(mixed)
}
