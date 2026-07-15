// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-capturing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Type capturing is not recursive and rejects nested projection mismatches
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> foo6(array: Array<Array<T>>): Array<Array<T>> = array

fun case_1(array: Array<Array<out Int>>) {
    val f: Array<out Array<out Int>> = foo6(<!TYPE_MISMATCH!>array<!>)
}


// TESTCASE NUMBER: 2
interface NumberWrapper7<S : Number>

fun case_2(x: NumberWrapper7<<!UPPER_BOUND_VIOLATED!>String<!>>) {}


// TESTCASE NUMBER: 3
interface Inv8<T>

fun case_3(x: Inv8<out Number>) {
    checkSubtype<Inv8<out Int>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 4
interface Inv9<T>

fun case_4(x: Inv9<in Int>) {
    checkSubtype<Inv9<in Number>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 5
interface Out10<out T>

fun case_5(a: Out10<Int>, b: Out10<Number>) {
    val x: Out10<Int> = <!TYPE_MISMATCH!>b<!>
}
