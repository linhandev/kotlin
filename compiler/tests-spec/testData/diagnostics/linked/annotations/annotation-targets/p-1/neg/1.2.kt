// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Target meta-annotation allowedTargets parameter must be AnnotationTarget enum
 */

// TESTCASE NUMBER: 1
@Target(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
annotation class BadTarget17312(val value: Int)
