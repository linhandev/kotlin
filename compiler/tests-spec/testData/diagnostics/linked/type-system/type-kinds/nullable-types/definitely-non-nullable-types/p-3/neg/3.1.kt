// FIR_IDENTICAL
// LANGUAGE: +DefinitelyNonNullableTypes
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, definitely-non-nullable-types -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Invalid intersection components in definitely non-nullable types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(t: T?) {
    val c: T & <!INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION!>Int<!> = t!!
}


// TESTCASE NUMBER: 2
fun <T : Any> case_2(q: T?) {
    val d: <!INCORRECT_LEFT_COMPONENT_OF_INTERSECTION!>T<!> & Any = q!!
}


// TESTCASE NUMBER: 3
fun <F> case_3(x: <!INCORRECT_LEFT_COMPONENT_OF_INTERSECTION!>F?<!> & Any) {}


// TESTCASE NUMBER: 4
fun <F> case_4(x: F & <!INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION!>String<!>) {}


// TESTCASE NUMBER: 5
fun <F> case_5(x: <!NULLABLE_ON_DEFINITELY_NOT_NULLABLE!>(F & Any)?<!>) {}
