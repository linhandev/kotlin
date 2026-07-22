// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-retention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Retention without arguments defaults to RUNTIME and compiles on annotation class
 */

// TESTCASE NUMBER: 1
@Retention
annotation class DefaultRetention17501

// TESTCASE NUMBER: 2
@Retention(AnnotationRetention.RUNTIME)
annotation class ExplicitRuntime17501
