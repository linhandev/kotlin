// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, specialized-iterator-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: built-in-types-and-their-semantics, specialized-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: specialized array iterators inherit kotlin.Iterator and expose typed next methods
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val it = intArrayOf(1, 2).iterator()
    checkSubtype<IntIterator>(it)
    checkSubtype<Iterator<Int>>(it)
    it.nextInt() checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val it = charArrayOf('a', 'b').iterator()
    checkSubtype<CharIterator>(it)
    it.nextChar() checkType { check<Char>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    val it = doubleArrayOf(1.0).iterator()
    it.nextDouble() checkType { check<Double>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    val it = floatArrayOf(1.0f).iterator()
    checkSubtype<FloatIterator>(it)
    it.nextFloat() checkType { check<Float>() }
}


// TESTCASE NUMBER: 5
fun case_5() {
    val it = longArrayOf(1L).iterator()
    checkSubtype<LongIterator>(it)
    it.nextLong() checkType { check<Long>() }
}


// TESTCASE NUMBER: 6
fun case_6() {
    val it = shortArrayOf(1).iterator()
    it.nextShort() checkType { check<Short>() }
}


// TESTCASE NUMBER: 7
fun case_7() {
    val it = byteArrayOf(1).iterator()
    it.nextByte() checkType { check<Byte>() }
}


// TESTCASE NUMBER: 8
fun case_8() {
    val it = booleanArrayOf(true).iterator()
    checkSubtype<BooleanIterator>(it)
    it.nextBoolean() checkType { check<Boolean>() }
}
