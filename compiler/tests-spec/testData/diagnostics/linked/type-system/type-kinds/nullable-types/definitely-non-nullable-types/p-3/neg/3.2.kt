// FIR_IDENTICAL
// LANGUAGE: +DefinitelyNonNullableTypes
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, definitely-non-nullable-types -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: DNN type constraints reject nested and malformed intersections
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <F> case_1(x: F & <!INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION!>(F & Any)<!>) {}


// TESTCASE NUMBER: 2
fun <F> case_2(x: <!INCORRECT_LEFT_COMPONENT_OF_INTERSECTION!>(F & Any)<!> & Any) {}


// TESTCASE NUMBER: 3
fun <F> case_3(x: F & <!INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION!>Any?<!>) {}


// TESTCASE NUMBER: 4
fun <F> case_4(x: <!INCORRECT_LEFT_COMPONENT_OF_INTERSECTION!>(F?)<!> & Any) {}


// TESTCASE NUMBER: 5
fun <F> case_5(x: F & <!INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION!>String<!>) {}
