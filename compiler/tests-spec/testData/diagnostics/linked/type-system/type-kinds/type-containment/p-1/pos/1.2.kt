// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-containment -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Type containment establishes type argument subtyping for projected types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv6<T>

fun case_1(invInt: Inv6<Int>, invNumber: Inv6<Number>) {
    checkSubtype<Inv6<in Int>>(invInt)
    checkSubtype<Inv6<in Int>>(invNumber)
}


// TESTCASE NUMBER: 2
interface Inv7<T>

fun case_2(invInt: Inv7<Int>, invNumber: Inv7<Number>) {
    checkSubtype<Inv7<out Number>>(invInt)
    checkSubtype<Inv7<out Number>>(invNumber)
}


// TESTCASE NUMBER: 3
interface Root8<T>

interface A8

interface B8 : A8

interface C8 : B8

interface Bounded8<T : A8> : Root8<T>

fun case_3(bounded: Bounded8<in B8>) {
    checkSubtype<Root8<in C8>>(bounded)
}


// TESTCASE NUMBER: 4
interface Inv9<T>

fun case_4(x: Inv9<Number>) {
    checkSubtype<Inv9<in Int>>(x)
    checkSubtype<Inv9<out Number>>(x)
}


// TESTCASE NUMBER: 5
interface Inv10<T>

fun case_5(x: Inv10<Int>) {
    checkSubtype<Inv10<*>>(x)
}
