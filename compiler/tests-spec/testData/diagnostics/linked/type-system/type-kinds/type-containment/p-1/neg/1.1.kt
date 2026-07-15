// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-containment -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type containment violations produce TYPE_MISMATCH for type argument subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<T>

fun case_1(x: Inv1<out Number>) {
    checkSubtype<Inv1<out Int>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 2
interface Inv2<T>

fun case_2(x: Inv2<in Int>) {
    checkSubtype<Inv2<in Number>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 3
interface Inv3<T>

fun case_3(inInt: Inv3<in Int>, outInt: Inv3<out Int>) {
    val a: Inv3<in Int> = <!TYPE_MISMATCH!>outInt<!>
    val b: Inv3<out Int> = <!TYPE_MISMATCH!>inInt<!>
}


// TESTCASE NUMBER: 4
interface Out4<out T>

fun case_4(a: Out4<String>, b: Out4<CharSequence>) {
    val x: Out4<String> = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 5
interface In5<in T>

fun case_5(a: In5<CharSequence>, b: In5<String>) {
    val x: In5<CharSequence> = <!TYPE_MISMATCH!>b<!>
}
