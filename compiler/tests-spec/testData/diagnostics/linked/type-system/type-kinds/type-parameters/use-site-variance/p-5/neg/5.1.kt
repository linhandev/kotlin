// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, use-site-variance -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Invalid assignments between invariant and projected types and contradictory variance combinations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<A>

fun case_1(invInt: Inv1<Int>, inInt: Inv1<in Int>, outInt: Inv1<out Int>) {
    val a: Inv1<Int> = <!TYPE_MISMATCH!>inInt<!>
    val b: Inv1<Int> = <!TYPE_MISMATCH!>outInt<!>
}


// TESTCASE NUMBER: 2
interface Inv2<A>

fun case_2(inInt: Inv2<in Int>, outInt: Inv2<out Int>, outNumber: Inv2<out Number>) {
    val a: Inv2<in Int> = <!TYPE_MISMATCH!>outInt<!>
    val b: Inv2<in Int> = <!TYPE_MISMATCH!>outNumber<!>
}


// TESTCASE NUMBER: 3
interface Out3<out A>

fun case_3(x: Out3<<!CONFLICTING_PROJECTION!>in<!> Int>) {}


// TESTCASE NUMBER: 4
interface In4<in A>

fun case_4(x: In4<<!CONFLICTING_PROJECTION!>out<!> Int>) {}


// TESTCASE NUMBER: 5
interface Inv5<A>

fun case_5(x: Inv5<Int>, y: Inv5<Number>) {
    checkSubtype<Inv5<Int>>(<!TYPE_MISMATCH!>y<!>)
    checkSubtype<Inv5<Number>>(<!TYPE_MISMATCH!>x<!>)
}
