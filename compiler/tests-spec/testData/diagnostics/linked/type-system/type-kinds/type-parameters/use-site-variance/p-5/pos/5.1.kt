// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, use-site-variance -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Use-site variance allows co-, contra- and invariant type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<A>

fun case_1(invInt: Inv1<Int>, invNumber: Inv1<Number>) {
    val inInt: Inv1<in Int> = invInt
    val inInt2: Inv1<in Int> = invNumber
    val outNumber: Inv1<out Number> = invInt
    val outNumber2: Inv1<out Number> = invNumber
}


// TESTCASE NUMBER: 2
interface Inv2<A>

fun case_2(x: Inv2<Int>) {
    checkSubtype<Inv2<in Int>>(x)
    checkSubtype<Inv2<out Number>>(x)
}


// TESTCASE NUMBER: 3
interface Inv3<A>

fun case_3(x: Inv3<Number>) {
    val y: Inv3<in Int> = x
    checkSubtype<Inv3<in Int>>(x)
}


// TESTCASE NUMBER: 4
interface Inv4<A>

fun case_4(x: Inv4<Int>) {
    val y: Inv4<out Number> = x
    checkSubtype<Inv4<out Number>>(x)
}


// TESTCASE NUMBER: 5
interface Inv5<A>

fun case_5(x: Inv5<Int>) {
    checkSubtype<Inv5<*>>(x)
    val y: Inv5<*> = x
}
