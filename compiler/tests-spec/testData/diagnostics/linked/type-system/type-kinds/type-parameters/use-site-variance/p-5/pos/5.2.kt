// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, use-site-variance -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: Use-site variance subtyping for invariant type constructors
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<A>

fun case_1(invInt: Inv1<Int>, invNumber: Inv1<Number>) {
    checkSubtype<Inv1<in Int>>(invInt)
    checkSubtype<Inv1<in Int>>(invNumber)
}


// TESTCASE NUMBER: 2
interface Inv2<A>

fun case_2(invInt: Inv2<Int>, invNumber: Inv2<Number>) {
    checkSubtype<Inv2<out Number>>(invInt)
    checkSubtype<Inv2<out Number>>(invNumber)
}


// TESTCASE NUMBER: 3
interface Inv3<A>

fun case_3(x: Inv3<String>) {
    checkSubtype<Inv3<out CharSequence>>(x)
    checkSubtype<Inv3<in String>>(x)
}


// TESTCASE NUMBER: 4
interface Inv4<A>

fun case_4(x: Inv4<Int>) {
    val a: Inv4<out Int> = x
    val b: Inv4<in Int> = x
}


// TESTCASE NUMBER: 5
interface Inv5<A>

fun case_5(x: Inv5<Double>) {
    checkSubtype<Inv5<in Double>>(x)
    checkSubtype<Inv5<out Number>>(x)
}
