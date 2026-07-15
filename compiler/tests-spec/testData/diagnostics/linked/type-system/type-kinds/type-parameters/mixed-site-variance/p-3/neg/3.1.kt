// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, mixed-site-variance -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Mixed-site variance prohibits contradictory declaration- and use-site variance combinations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Out1<out A>

fun case_1(x: Out1<<!CONFLICTING_PROJECTION!>in<!> Int>) {}


// TESTCASE NUMBER: 2
interface In2<in A>

fun case_2(x: In2<<!CONFLICTING_PROJECTION!>out<!> Int>) {}


// TESTCASE NUMBER: 3
interface Inv3<A>

fun case_3(inInt: Inv3<in Int>, outInt: Inv3<out Int>) {
    val a: Inv3<in Int> = <!TYPE_MISMATCH!>outInt<!>
    val b: Inv3<out Int> = <!TYPE_MISMATCH!>inInt<!>
}


// TESTCASE NUMBER: 4
interface Inv4<A>

fun case_4(invInt: Inv4<Int>, inInt: Inv4<in Int>, outInt: Inv4<out Int>) {
    val a: Inv4<Int> = <!TYPE_MISMATCH!>inInt<!>
    val b: Inv4<Int> = <!TYPE_MISMATCH!>outInt<!>
}


// TESTCASE NUMBER: 5
interface In5<in A>

interface Out5<out A>

fun case_5(x: In5<Out5<<!CONFLICTING_PROJECTION!>in<!> Int>>) {}
