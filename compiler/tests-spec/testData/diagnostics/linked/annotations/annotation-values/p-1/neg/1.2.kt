// FIR_IDENTICAL
// LANGUAGE: +ProhibitCyclesInAnnotations
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: indirect cyclic annotation type references are forbidden
 */

// TESTCASE NUMBER: 1
annotation class CyclicZ117112(<!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val a: CyclicZ217112<!>, <!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val b: CyclicZ217112<!>)

annotation class CyclicZ217112(<!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val value: CyclicZ117112<!>)
