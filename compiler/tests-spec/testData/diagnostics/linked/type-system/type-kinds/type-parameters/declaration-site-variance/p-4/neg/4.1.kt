// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, declaration-site-variance -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Invariant type parameters do not create subtyping between parameterized types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Invariant1<A>

fun case_1(invInt: Invariant1<Int>, invNumber: Invariant1<Number>) {
    val a: Invariant1<Int> = <!TYPE_MISMATCH!>invNumber<!>
    val b: Invariant1<Number> = <!TYPE_MISMATCH!>invInt<!>
}


// TESTCASE NUMBER: 2
interface Out2<out A>

fun case_2(outInt: Out2<Int>, outNumber: Out2<Number>) {
    val a: Out2<Int> = <!TYPE_MISMATCH!>outNumber<!>
    checkSubtype<Out2<Int>>(<!TYPE_MISMATCH!>outNumber<!>)
}


// TESTCASE NUMBER: 3
interface In3<in A>

fun case_3(inInt: In3<Int>, inNumber: In3<Number>) {
    val a: In3<Number> = <!TYPE_MISMATCH!>inInt<!>
    checkSubtype<In3<Number>>(<!TYPE_MISMATCH!>inInt<!>)
}


// TESTCASE NUMBER: 4
interface Invariant4<A>

fun case_4(x: Invariant4<Int>, y: Invariant4<Number>) {
    checkSubtype<Invariant4<Number>>(<!TYPE_MISMATCH!>x<!>)
    checkSubtype<Invariant4<Int>>(<!TYPE_MISMATCH!>y<!>)
}


// TESTCASE NUMBER: 5
interface Out5<out A>

fun case_5(outInt: Out5<Int>, outNumber: Out5<Number>) {
    checkSubtype<Out5<Int>>(<!TYPE_MISMATCH!>outNumber<!>)
    val z: Out5<Int> = <!TYPE_MISMATCH!>outNumber<!>
}
