// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, mixed-site-variance -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Mixed-site variance subtyping with invariant type constructors and projections
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
interface Out3<out A>

fun case_3(x: Out3<String>) {
    checkSubtype<Out3<CharSequence>>(x)
    checkSubtype<Out3<*>>(x)
}


// TESTCASE NUMBER: 4
interface In4<in A>

fun case_4(x: In4<CharSequence>) {
    checkSubtype<In4<String>>(x)
    checkSubtype<In4<*>>(x)
}


// TESTCASE NUMBER: 5
interface Inv5<A>

fun case_5(x: Inv5<Double>) {
    checkSubtype<Inv5<in Double>>(x)
    checkSubtype<Inv5<out Number>>(x)
    checkSubtype<Inv5<*>>(x)
}
