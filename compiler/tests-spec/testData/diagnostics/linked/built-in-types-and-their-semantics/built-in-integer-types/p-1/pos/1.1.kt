// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-integer-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: built-in integer types are subtypes of kotlin.Comparable
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val i: Int = 1
    checkSubtype<Comparable<Int>>(i)
    i.compareTo(2) checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s: Short = 1
    checkSubtype<Comparable<Short>>(s)
    s.compareTo(2.toShort()) checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    val b: Byte = 1
    checkSubtype<Comparable<Byte>>(b)
    b.compareTo(2.toByte()) checkType { check<Int>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    val l: Long = 1L
    checkSubtype<Comparable<Long>>(l)
    l.compareTo(2L) checkType { check<Int>() }
}


// TESTCASE NUMBER: 5
fun case_5(i: Int, s: Short, b: Byte, l: Long) {
    checkSubtype<Comparable<Int>>(i)
    checkSubtype<Comparable<Short>>(s)
    checkSubtype<Comparable<Byte>>(b)
    checkSubtype<Comparable<Long>>(l)
}
