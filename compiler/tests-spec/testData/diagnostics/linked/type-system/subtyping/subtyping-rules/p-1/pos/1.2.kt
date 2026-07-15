// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -REDUNDANT_PROJECTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-rules -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Simple classifier and parameterized type subtyping rules
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
open class Base1
class Derived1 : Base1()
fun case_1(d: Derived1): Base1 {
    checkSubtype<Base1>(d)
    return d
}

interface I2
class C2 : I2


// TESTCASE NUMBER: 2
fun case_2(c: C2): I2 {
    checkSubtype<I2>(c)
    return c
}


// TESTCASE NUMBER: 3
fun case_3() {
    val list: List<Int> = listOf(1)
    val up: List<Number> = list
    checkSubtype<List<Number>>(up)
}

interface Out4<out T>
class IntOut4 : Out4<Int>


// TESTCASE NUMBER: 4
fun case_4(x: IntOut4): Out4<Number> {
    checkSubtype<Out4<Number>>(x)
    return x
}


// TESTCASE NUMBER: 5
fun case_5() {
    val i: Int = 1
    checkSubtype<Any>(i)
}
