// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Subtyping as substitutability allows subtype values where supertype is expected
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
open class Base1
class Derived1 : Base1()
fun case_1() {
    val d: Derived1 = Derived1()
    val b: Base1 = d
    checkSubtype<Base1>(b)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val i: Int = 1
    val n: Number = i
    checkSubtype<Number>(n)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val s: String = "ok"
    val a: Any = s
    checkSubtype<Any>(a)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val b: Boolean = true
    val a: Any = b
    checkSubtype<Any>(a)
}


interface I5
class C5 : I5


// TESTCASE NUMBER: 5
fun case_5() {
    val c: C5 = C5()
    val i: I5 = c
    checkSubtype<I5>(i)
}
