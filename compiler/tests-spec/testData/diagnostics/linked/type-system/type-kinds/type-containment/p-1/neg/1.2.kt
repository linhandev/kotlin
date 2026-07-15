// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-containment -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Invariant and mixed projections fail containment when subtyping does not hold
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv6<T>

fun case_1(x: Inv6<Int>, y: Inv6<Number>) {
    checkSubtype<Inv6<Int>>(<!TYPE_MISMATCH!>y<!>)
    checkSubtype<Inv6<Number>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 2
interface Inv7<T>

fun case_2(inInt: Inv7<in Int>, outNumber: Inv7<out Number>) {
    checkSubtype<Inv7<in Int>>(<!TYPE_MISMATCH!>outNumber<!>)
    checkSubtype<Inv7<out Number>>(<!TYPE_MISMATCH!>inInt<!>)
}


// TESTCASE NUMBER: 3
interface Inv8<T>

fun case_3(invInt: Inv8<Int>, inInt: Inv8<in Int>, outInt: Inv8<out Int>) {
    val a: Inv8<Int> = <!TYPE_MISMATCH!>inInt<!>
    val b: Inv8<Int> = <!TYPE_MISMATCH!>outInt<!>
}


// TESTCASE NUMBER: 4
interface Out9<out T>

fun case_4(a: Out9<Int>, b: Out9<Long>) {
    checkSubtype<Out9<Int>>(<!TYPE_MISMATCH!>b<!>)
}


// TESTCASE NUMBER: 5
interface In10<in T>

fun case_5(a: In10<Int>, b: In10<Long>) {
    checkSubtype<In10<Long>>(<!TYPE_MISMATCH!>a<!>)
}
