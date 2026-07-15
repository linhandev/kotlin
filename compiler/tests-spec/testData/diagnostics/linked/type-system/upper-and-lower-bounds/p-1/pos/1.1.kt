// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type parameter upper bounds constrain substitutability
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T : Number> case_1(x: T): Number {
    checkSubtype<Number>(x)
    return x
}

// TESTCASE NUMBER: 2
fun <T : Comparable<T>> case_2(x: T): Comparable<T> {
    checkSubtype<Comparable<T>>(x)
    return x
}

// TESTCASE NUMBER: 3
class Box3<T : CharSequence>(val value: T)

fun case_3(b: Box3<String>) {
    checkSubtype<CharSequence>(b.value)
}

// TESTCASE NUMBER: 4
interface Holder4<T : Any> {
    fun get(): T
}

fun case_4(h: Holder4<Int>): Any {
    return h.get()
}

// TESTCASE NUMBER: 5
fun <T : Enum<T>> case_5(x: T): Enum<*> {
    checkSubtype<Enum<*>>(x)
    return x
}
