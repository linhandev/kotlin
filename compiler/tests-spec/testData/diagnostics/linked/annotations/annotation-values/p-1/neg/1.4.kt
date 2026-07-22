// FIR_IDENTICAL
// LANGUAGE: +ProhibitCyclesInAnnotations
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: direct self-referential annotation type is forbidden
 */

// TESTCASE NUMBER: 1
annotation class SelfRef17114(<!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val value: SelfRef17114<!>)
