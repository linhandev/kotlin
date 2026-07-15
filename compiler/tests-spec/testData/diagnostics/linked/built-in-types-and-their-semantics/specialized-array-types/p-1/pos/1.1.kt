// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, specialized-array-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, array-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: specialized array factory functions produce kotlin.IntArray, kotlin.DoubleArray and related types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: IntArray = intArrayOf(1, 2, 3)
    checkSubtype<IntArray>(a)
    a[0] checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a: DoubleArray = doubleArrayOf(1.0, 2.0)
    checkSubtype<DoubleArray>(a)
    a.size checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a: BooleanArray = booleanArrayOf(true, false)
    checkSubtype<BooleanArray>(a)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val a: CharArray = charArrayOf('a', 'b')
    checkSubtype<CharArray>(a)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val a: FloatArray = floatArrayOf(1.0f, 2.0f)
    checkSubtype<FloatArray>(a)
    a[0] checkType { check<Float>() }
}


// TESTCASE NUMBER: 6
fun case_6() {
    val a: ShortArray = shortArrayOf(1, 2)
    checkSubtype<ShortArray>(a)
    a.size checkType { check<Int>() }
}


// TESTCASE NUMBER: 7
fun case_7() {
    val a: ByteArray = byteArrayOf(1, 2)
    checkSubtype<ByteArray>(a)
    a[1] checkType { check<Byte>() }
}
