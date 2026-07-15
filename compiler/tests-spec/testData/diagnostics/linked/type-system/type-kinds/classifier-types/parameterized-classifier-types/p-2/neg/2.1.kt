// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Ill-formed type constructors and parameterized types violate well-formedness conditions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Generic1<A, B>

interface Invalid1<P> : <!WRONG_NUMBER_OF_TYPE_ARGUMENTS!>Generic1<!>


// TESTCASE NUMBER: 2
interface Generic2<A, B>

interface InvalidDerived2 : <!WRONG_NUMBER_OF_TYPE_ARGUMENTS!>Generic2<!>


// TESTCASE NUMBER: 3
interface Generic3<P, Q>

interface InvalidDerived3<P> : Generic3<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int><!>


// TESTCASE NUMBER: 4
interface Out4<out A>

interface In4<in A>

interface Invalid4<X> : Generic2<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><X><!>


// TESTCASE NUMBER: 5
interface NumberWrapper5<S : Number>

interface InvalidWrapper5 : NumberWrapper5<<!UPPER_BOUND_VIOLATED!>String<!>>
