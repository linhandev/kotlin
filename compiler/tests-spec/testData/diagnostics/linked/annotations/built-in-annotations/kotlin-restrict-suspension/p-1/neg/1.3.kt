// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-restrict-suspension -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: RestrictsSuspension can only be applied to class declaration
 */

// TESTCASE NUMBER: 1
<!WRONG_ANNOTATION_TARGET!>@kotlin.coroutines.RestrictsSuspension<!>
fun badRestrictSuspension17763() {}
