// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, declaration-site-variance -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Declaration-site variance creates subtyping between parameterized types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Out1<out A>

fun case_1(outInt: Out1<Int>, outNumber: Out1<Number>) {
    checkSubtype<Out1<Number>>(outInt)
    val x: Out1<Number> = outInt
}


// TESTCASE NUMBER: 2
interface In2<in A>

fun case_2(inInt: In2<Int>, inNumber: In2<Number>) {
    checkSubtype<In2<Int>>(inNumber)
    val x: In2<Int> = inNumber
}


// TESTCASE NUMBER: 3
interface Invariant3<A>

fun case_3(x: Invariant3<Int>) {
    checkSubtype<Invariant3<Int>>(x)
}


// TESTCASE NUMBER: 4
interface Out4<out A>

class IntOut4 : Out4<Int>

fun case_4(x: IntOut4) {
    checkSubtype<Out4<Number>>(x)
}


// TESTCASE NUMBER: 5
interface In5<in A>

class NumberIn5 : In5<Number> {
    override fun toString(): String = "ok"
}

fun case_5(x: NumberIn5) {
    checkSubtype<In5<Int>>(x)
}
