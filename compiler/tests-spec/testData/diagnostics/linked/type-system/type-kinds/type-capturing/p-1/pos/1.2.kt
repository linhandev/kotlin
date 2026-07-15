// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-capturing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Invariant type arguments produce captured types equivalent to the argument type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box6<T>

fun case_1(x: Box6<Int>) {
    checkSubtype<Box6<Int>>(x)
    val y: Box6<Int> = x
}


// TESTCASE NUMBER: 2
interface Out7<out T>

fun case_2(x: Out7<Int>) {
    checkSubtype<Out7<Number>>(x)
}


// TESTCASE NUMBER: 3
interface In8<in T>

fun case_3(x: In8<Number>) {
    checkSubtype<In8<Int>>(x)
}


// TESTCASE NUMBER: 4
interface Inv9<T>

fun case_4(invInt: Inv9<Int>, invNumber: Inv9<Number>) {
    checkSubtype<Inv9<out Number>>(invInt)
    checkSubtype<Inv9<in Int>>(invNumber)
}


// TESTCASE NUMBER: 5
interface NumberWrapper10<S : Number>

fun case_5(x: NumberWrapper10<Int>) {
    checkSubtype<NumberWrapper10<Int>>(x)
    checkSubtype<NumberWrapper10<out Number>>(x)
}
