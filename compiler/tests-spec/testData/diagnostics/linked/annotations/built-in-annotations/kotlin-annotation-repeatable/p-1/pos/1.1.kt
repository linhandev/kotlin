// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-repeatable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Repeatable meta-annotation marks annotation class as repeatable
 */

// TESTCASE NUMBER: 1
@Repeatable
annotation class RepeatableBuiltin17551(val value: Int)

@RepeatableBuiltin17551(1)
@RepeatableBuiltin17551(2)
class RepeatedBuiltin17551
