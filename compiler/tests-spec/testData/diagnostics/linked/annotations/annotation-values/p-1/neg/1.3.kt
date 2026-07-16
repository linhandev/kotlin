// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: annotation class cannot declare Class parameter type
 */

// TESTCASE NUMBER: 1
annotation class BadClassParam17113(val p: <!INVALID_TYPE_OF_ANNOTATION_MEMBER!>Class<*><!>)
