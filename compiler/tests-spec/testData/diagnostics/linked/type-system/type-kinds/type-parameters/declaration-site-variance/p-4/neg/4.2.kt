// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, declaration-site-variance -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Declaration-site variance subtyping violations for covariant and contravariant type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Out1<out A>

fun case_1(a: Out1<String>, b: Out1<CharSequence>) {
    val x: Out1<String> = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 2
interface In2<in A>

fun case_2(a: In2<CharSequence>, b: In2<String>) {
    val x: In2<CharSequence> = <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 3
interface Invariant3<A>

fun case_3(a: Invariant3<Int>, b: Invariant3<Double>) {
    val x: Invariant3<Int> = <!TYPE_MISMATCH!>b<!>
    val y: Invariant3<Double> = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 4
interface Out4<out A>

fun case_4(a: Out4<Int>, b: Out4<Long>) {
    checkSubtype<Out4<Int>>(<!TYPE_MISMATCH!>b<!>)
}


// TESTCASE NUMBER: 5
interface In5<in A>

fun case_5(a: In5<Int>, b: In5<Long>) {
    checkSubtype<In5<Long>>(<!TYPE_MISMATCH!>a<!>)
}
