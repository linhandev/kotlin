// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: cyclic annotation type references are forbidden
 */

// TESTCASE NUMBER: 1
annotation class Z1(<!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val a: Z2<!>)
annotation class Z2(<!CYCLE_IN_ANNOTATION_PARAMETER_ERROR!>val value: Z1<!>)
