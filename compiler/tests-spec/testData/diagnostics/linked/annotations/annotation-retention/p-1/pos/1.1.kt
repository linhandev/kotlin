// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-retention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: SOURCE BINARY and RUNTIME retention levels may be declared on annotation classes
 */

// TESTCASE NUMBER: 1
@Retention(AnnotationRetention.SOURCE)
annotation class SourceRetention17201

// TESTCASE NUMBER: 2
@Retention(AnnotationRetention.BINARY)
annotation class BinaryRetention17201

// TESTCASE NUMBER: 3
@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeRetention17201
