// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, fully-qualified-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: fully-qualified call with non-existent package path fails resolution
 */

// TESTCASE NUMBER: 1
fun case_1(): Int = <!UNRESOLVED_REFERENCE!>pkg11201<!>.missing.pick11201()
