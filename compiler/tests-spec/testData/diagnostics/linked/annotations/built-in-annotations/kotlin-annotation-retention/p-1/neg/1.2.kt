// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-retention -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Retention value parameter must be AnnotationRetention enum
 */

// TESTCASE NUMBER: 1
@Retention(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
annotation class BadRetention17512
