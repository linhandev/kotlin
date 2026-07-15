// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type parameters are well-formed concrete types in their declaring type constructor context
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box1<T>(val value: T)

fun case_1() {
    val box = Box1(1)
    checkSubtype<Int>(box.value)
}


// TESTCASE NUMBER: 2
interface Holder2<T> {
    fun get(): T
}

fun case_2(h: Holder2<String>) {
    checkSubtype<String>(h.get())
}


// TESTCASE NUMBER: 3
class Pair3<A, B>(val first: A, val second: B)

fun case_3() {
    val pair = Pair3("a", 1)
    checkSubtype<String>(pair.first)
    checkSubtype<Int>(pair.second)
}


// TESTCASE NUMBER: 4
class Parent4<T> {
    inner class Inner4(val t: T)
}

fun case_4() {
    val inner = Parent4<Int>().Inner4(1)
    checkSubtype<Int>(inner.t)
}


// TESTCASE NUMBER: 5
class Unbounded5<T>

fun case_5() {
    val box = Box1(Unbounded5<Unit>())
    checkSubtype<Unbounded5<Unit>>(box.value)
}
