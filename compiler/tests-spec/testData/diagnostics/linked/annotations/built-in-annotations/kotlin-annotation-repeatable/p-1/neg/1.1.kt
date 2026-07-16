// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-repeatable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-repeatable annotation cannot be applied twice to the same entity
 */

// TESTCASE NUMBER: 1
annotation class NonRepeatable17561(val value: Int)

@NonRepeatable17561(1) <!REPEATED_ANNOTATION!>@NonRepeatable17561(2)<!> class TwiceAnnotated17561
