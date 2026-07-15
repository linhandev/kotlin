// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, mixed-site-variance -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Mixed-site variance subtyping violations for declaration-site variant type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Out1<out A>

fun case_1(outInt: Out1<Int>, outNumber: Out1<Number>) {
    val x: Out1<Int> = <!TYPE_MISMATCH!>outNumber<!>
    checkSubtype<Out1<Int>>(<!TYPE_MISMATCH!>outNumber<!>)
}


// TESTCASE NUMBER: 2
interface In2<in A>

fun case_2(inInt: In2<Int>, inNumber: In2<Number>) {
    val x: In2<Number> = <!TYPE_MISMATCH!>inInt<!>
    checkSubtype<In2<Number>>(<!TYPE_MISMATCH!>inInt<!>)
}


// TESTCASE NUMBER: 3
interface Invariant3<A>

fun case_3(a: Invariant3<Int>, b: Invariant3<Number>) {
    val x: Invariant3<Int> = <!TYPE_MISMATCH!>b<!>
    val y: Invariant3<Number> = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 4
interface Inv4<A>

fun case_4(inInt: Inv4<in Int>, outNumber: Inv4<out Number>) {
    checkSubtype<Inv4<in Int>>(<!TYPE_MISMATCH!>outNumber<!>)
    checkSubtype<Inv4<out Number>>(<!TYPE_MISMATCH!>inInt<!>)
}


// TESTCASE NUMBER: 5
interface Out5<out A>

fun case_5(a: Out5<String>, b: Out5<CharSequence>) {
    val x: Out5<String> = <!TYPE_MISMATCH!>b<!>
}
