// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Subtyping is reflexive and transitively usable for assignment
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
open class Base2
open class Middle2 : Base2()
class Derived2 : Middle2()
fun case_1() {
    val i: Int = 42
    val copy: Int = i
    checkSubtype<Int>(copy)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val d: Derived2 = Derived2()
    val m: Middle2 = d
    val b: Base2 = m
    checkSubtype<Base2>(b)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val d: Derived2 = Derived2()
    val b: Base2 = d
    checkSubtype<Base2>(b)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x: Double = 1.0
    val n: Number = x
    val a: Any = n
    checkSubtype<Any>(a)
}


// TESTCASE NUMBER: 5
fun case_5(s: String) {
    val cs: CharSequence = s
    val a: Any = cs
    checkSubtype<Any>(a)
}
