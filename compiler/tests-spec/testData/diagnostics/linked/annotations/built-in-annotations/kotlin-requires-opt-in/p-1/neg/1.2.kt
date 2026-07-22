// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.RequiresOptIn
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-requires-opt-in -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: RequiresOptIn message parameter must be String
 */

// TESTCASE NUMBER: 1
@RequiresOptIn(message = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
annotation class BadRequires17552
