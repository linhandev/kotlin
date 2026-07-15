// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-capturing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type capturing fails when captured types cannot satisfy subtyping constraints
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> foo1(a1: Array<T>, a2: Array<out T>): T = null!!

fun case_1(a1: Array<in Int>, a2: Array<Int>) {
    val c: Int = <!TYPE_MISMATCH, TYPE_MISMATCH!>foo1(a1, a2)<!>
}


// TESTCASE NUMBER: 2
fun <T> foo2(array: Array<Array<T>>): Array<Array<T>> = array

fun case_2(array: Array<Array<out Int>>) {
    foo2(<!TYPE_MISMATCH!>array<!>)
}


// TESTCASE NUMBER: 3
interface Root3<T>

interface A3

interface B3 : A3

interface C3 : A3

interface Bounded3<T : A3> : Root3<T>

fun case_3(x: Bounded3<in B3>) {
    val y: Root3<in C3> = <!TYPE_MISMATCH!>x<!>
}


// TESTCASE NUMBER: 4
interface Out4<out T>

fun case_4(x: Out4<<!CONFLICTING_PROJECTION!>in<!> Int>) {}


// TESTCASE NUMBER: 5
interface In5<in T>

fun case_5(x: In5<<!CONFLICTING_PROJECTION!>out<!> Int>) {}
