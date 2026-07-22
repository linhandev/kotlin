// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-repeatable annotation cannot be applied twice to the same entity
 */

// TESTCASE NUMBER: 1
annotation class NonRepeatable17411(val value: Int)

@NonRepeatable17411(1) <!REPEATED_ANNOTATION!>@NonRepeatable17411(2)<!> class TwiceAnnotated17411
