// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-containment -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, type-capturing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type containment A ⪯ out B when A <: B and A ⪯ in B when A :> B
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<T>

fun case_1(x: Inv1<Int>) {
    checkSubtype<Inv1<out Number>>(x)
    val y: Inv1<out Number> = x
}


// TESTCASE NUMBER: 2
interface Inv2<T>

fun case_2(x: Inv2<Number>) {
    checkSubtype<Inv2<in Int>>(x)
    val y: Inv2<in Int> = x
}


// TESTCASE NUMBER: 3
interface Out3<out T>

fun case_3(x: Out3<Int>) {
    checkSubtype<Out3<Number>>(x)
}


// TESTCASE NUMBER: 4
interface In4<in T>

fun case_4(x: In4<Number>) {
    checkSubtype<In4<Int>>(x)
}


// TESTCASE NUMBER: 5
interface Inv5<T>

fun case_5(x: Inv5<String>) {
    checkSubtype<Inv5<String>>(x)
    checkSubtype<Inv5<out CharSequence>>(x)
}
