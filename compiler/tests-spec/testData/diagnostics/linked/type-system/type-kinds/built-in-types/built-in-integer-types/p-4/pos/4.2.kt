// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, constant-literals, the-types-for-integer-literals -> paragraph 1 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Nullable signed integer types, primitive arrays, and mixed arithmetic use built-in integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Int? = 1
    val b: Short? = 2
    val c: Byte? = 3
    val d: Long? = 4L
    checkSubtype<Int?>(a)
    checkSubtype<Short?>(b)
    checkSubtype<Byte?>(c)
    checkSubtype<Long?>(d)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr: IntArray = intArrayOf(1, 2, 3)
    val shortArr: ShortArray = shortArrayOf(1, 2)
    val byteArr: ByteArray = byteArrayOf(1)
    val longArr: LongArray = longArrayOf(1L)
    checkSubtype<IntArray>(arr)
    checkSubtype<ShortArray>(shortArr)
    checkSubtype<ByteArray>(byteArr)
    checkSubtype<LongArray>(longArr)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Number = 10
    checkSubtype<Int>(x as Int)
    checkSubtype<Short>(x as Short)
    checkSubtype<Byte>(x as Byte)
    checkSubtype<Long>(x as Long)
}


// TESTCASE NUMBER: 4
class Case4(val i: Int, val s: Short, val b: Byte, val l: Long)

fun case_4(c: Case4) {
    checkSubtype<Int>(c.i)
    checkSubtype<Short>(c.s)
    checkSubtype<Byte>(c.b)
    checkSubtype<Long>(c.l)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val sum: Long = 1 + 2.toShort() + 3.toByte() + 4L
    checkSubtype<Long>(sum)
}
