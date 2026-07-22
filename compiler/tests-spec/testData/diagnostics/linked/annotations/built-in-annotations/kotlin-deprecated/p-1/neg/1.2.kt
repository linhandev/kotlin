// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Deprecated level parameter must be DeprecationLevel enum
 */

// TESTCASE NUMBER: 1
@Deprecated("deprecated", level = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
fun badLevel17612() {}
