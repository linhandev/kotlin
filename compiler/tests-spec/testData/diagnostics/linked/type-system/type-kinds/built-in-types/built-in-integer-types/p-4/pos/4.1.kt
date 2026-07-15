// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, constant-literals, the-types-for-integer-literals -> paragraph 1 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: kotlin.Int, kotlin.Short, kotlin.Byte, and kotlin.Long are supported non-null signed integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(1)
    val x: Int = 42
    fun f(): Int = x
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Short>(1.toShort())
    val x: Short = 100
    fun f(): Short = x
}


// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Byte>(1.toByte())
    val x: Byte = 10
    fun f(): Byte = x
}


// TESTCASE NUMBER: 4
fun case_4() {
    checkSubtype<Long>(1L)
    val x: Long = 9223372036854775807L
    fun f(): Long = x
}


// TESTCASE NUMBER: 5
fun case_5(x: Int, y: Short, z: Byte, w: Long) {
    checkSubtype<Int>(x)
    checkSubtype<Short>(y)
    checkSubtype<Byte>(z)
    checkSubtype<Long>(w)
}
