// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, mixed-site-variance -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Mixed-site variance combines declaration-site and use-site variance for subtyping
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
interface Inv3<A>

fun case_3(invInt: Inv3<Int>, invNumber: Inv3<Number>) {
    checkSubtype<Inv3<out Number>>(invInt)
    checkSubtype<Inv3<in Int>>(invNumber)
}


// TESTCASE NUMBER: 4
interface Inv4<A>

fun case_4(x: Inv4<Int>) {
    val outNumber: Inv4<out Number> = x
    val inInt: Inv4<in Int> = x
}


// TESTCASE NUMBER: 5
interface Out5<out A>

fun case_5(x: Out5<Int>) {
    checkSubtype<Out5<Number>>(x)
    checkSubtype<Out5<*>>(x)
}
